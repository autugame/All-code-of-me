package com.qfedu.fmmall.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qfedu.fmmall.dao.*;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductParamsMapper productParamsMapper;

    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final Gson gson = new Gson();



    @Override
    public ResultVO listRecommendProduct() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        return new ResultVO(ResStatus.OK, "success", productVOS);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO getProductBasicInfo(String productId) {
        //Query redis based on the productId.
        String productInfo = (String) stringRedisTemplate.boundHashOps("products").get(productId);

        //If redis finds product information and then return it.
        if (productInfo != null) {
            Product product = gson.fromJson(productInfo, Product.class);
            String ImgInfo = (String) stringRedisTemplate.boundHashOps("productImgs").get(productId);
            List<ProductImg> productImgs = gson.fromJson(ImgInfo, new TypeToken<List<ProductImg>>() {}.getType());
            String skusInfo = (String) stringRedisTemplate.boundHashOps("productSkus").get(productId);
            List<ProductSku> productSkus = gson.fromJson(skusInfo, new TypeToken<List<ProductSku>>() {}.getType());

            HashMap<String, Object> basicInfo = new HashMap<>();
            basicInfo.put("product", product);
            basicInfo.put("productImgs", productImgs);
            basicInfo.put("productSkus", productSkus);
            return new ResultVO(ResStatus.OK, "success", basicInfo);
        } else {
            //If not and then query the database.
            //Basic product information.
            Example example = new Example(Product.class);
            example.createCriteria()
                    .andEqualTo("productId", productId)
                    .andEqualTo("productStatus", 1);

            List<Product> products = productMapper.selectByExample(example);

            if (!products.isEmpty()) {
                //Write data to redis.
                String jsonStr = gson.toJson(products.get(0));
                stringRedisTemplate.boundHashOps("products").put(productId, jsonStr);

                //Product picture.
                Example example1 = new Example(ProductImg.class);
                example1.createCriteria()
                        .andEqualTo("itemId", productId);
                List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
                stringRedisTemplate.boundHashOps("productImgs").put(productId, gson.toJson(productImgs));

                //Product package.
                Example example2 = new Example(ProductSku.class);
                example2.createCriteria()
                        .andEqualTo("productId", productId)
                        .andEqualTo("status", 1);
                List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);
                stringRedisTemplate.boundHashOps("productSkus").put(productId, gson.toJson(productSkus));

                HashMap<String, Object> basicInfo = new HashMap<>();
                basicInfo.put("product", products.get(0));
                basicInfo.put("productImgs", productImgs);
                basicInfo.put("productSkus", productSkus);
                return new ResultVO(ResStatus.OK, "success", basicInfo);
            } else {
                return new ResultVO(ResStatus.NO, "error");
            }
        }
    }

    @Override
    public ResultVO getProductParamsById(String productId) {
        Example example = new Example(ProductParams.class);
        example.createCriteria()
                        .andEqualTo("productId", productId);
        List<ProductParams> productParams = productParamsMapper.selectByExample(example);
        if (!productParams.isEmpty()) {
            return new ResultVO(ResStatus.OK, "success", productParams.get(0));
        } else {
            return new ResultVO(ResStatus.NO, "No parameter");
        }
    }

    @Override
    public ResultVO listCommentsByProductId(String productId, int pageNum, int limit) {
//        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommentsByProductId(productId);
        //Query the total number of records based on the product id.
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria()
                .andEqualTo("productId", productId);
        int count = productCommentsMapper.selectCountByExample(example);
        //Calculate total pages.
        int pageCount = count % limit == 0 ? count / limit : count / limit+1;
        //Query the data of current page.
        int start = (pageNum-1)*limit;
        List<ProductCommentsVO> list = productCommentsMapper.selectCommentsByProductId(productId, start, limit);
        return new ResultVO(ResStatus.OK, "success", new PageHelper<ProductCommentsVO>(count, pageCount, list));
    }

    @Override
    public ResultVO getCommentsCountByProductId(String productId) {
        //1.Query the total number of current product reviews.
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria()
                .andEqualTo("productId", productId);
        int total = productCommentsMapper.selectCountByExample(example);
        //2.Query the total number of praise reviews.
        criteria.andEqualTo("commType", 1);
        int goodTotal = productCommentsMapper.selectCountByExample(example);
        //3.Query the total number of medium reviews.
        Example example1 = new Example(ProductComments.class);
        example1.createCriteria()
                .andEqualTo("productId", productId)
                .andEqualTo("commType", 0);
        int midTotal = productCommentsMapper.selectCountByExample(example1);
        //4.Query the total number of negative reviews.
        Example example2 = new Example(ProductComments.class);
        example2.createCriteria()
                .andEqualTo("productId", productId)
                .andEqualTo("commType", -1);
        int badTotal = productCommentsMapper.selectCountByExample(example2);
        //5.Calculated praise rate.
        double percent = (Double.parseDouble(goodTotal+"") / Double.parseDouble(total+"")) * 100;
        String percentValue = String.format("%.2f", percent);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("goodTotal", goodTotal);
        map.put("midTotal", midTotal);
        map.put("badTotal", badTotal);
        map.put("percent", percentValue);
        return new ResultVO(ResStatus.OK, "success", map);
    }

    @Override
    public ResultVO getProductsByCategoryId(int categoryId, int pageNum, int limit) {
        //Query paging data.
        int start = (pageNum - 1) * limit;
        List<ProductVO> productVOS = productMapper.selectProductByCategoryId(categoryId, start, limit);
        //Query the total number of product records.
        Example example = new Example(Product.class);
        example.createCriteria()
                        .andEqualTo("categoryId", categoryId);
        int count = productMapper.selectCountByExample(example);
        //Calculate total pages.
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;
        //Encapsulated return data.
        return new ResultVO(ResStatus.OK, "success", new PageHelper<ProductVO>(count, pageCount, productVOS));
    }

    @Override
    public ResultVO listBrands(int categoryId) {
        return new ResultVO(ResStatus.OK, "success", productMapper.selectBrandByCategoryId(categoryId));
    }

    @Override
    public ResultVO searchProduct(String keyword, int pageNum, int limit) {
        //Query search results.

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(keyword, "productName", "productSkuName"))
                .withPageable(PageRequest.of(pageNum -   1, limit))
                .withHighlightFields(new HighlightBuilder.Field("productName").preTags("<label style='color: red'>").postTags("</label>"), new HighlightBuilder.Field("productSkuName").preTags("<label style='color: red'>").postTags("</label>"))
                .build();

        SearchHits<Product4ES> search = elasticsearchRestTemplate.search(query, Product4ES.class, IndexCoordinates.of("products"));

        //Processing data.
        long count = search.getTotalHits();

        List<SearchHit<Product4ES>> searchHits = search.getSearchHits();
        List<Product4ES> list = new ArrayList<>();
        for (SearchHit<Product4ES> hit : searchHits) {
            Product4ES product4ES = hit.getContent();
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            List<String> productName = highlightFields.get("productName");
            if (!productName.isEmpty()) {
                product4ES.setProductName(productName.get(0));
            }
            list.add(product4ES);
        }

        //Calculate total pages.
        int pageCount = ((int) (count % limit == 0 ? count / limit : count / limit + 1));
        return new ResultVO(ResStatus.OK, "success", new PageHelper<Product4ES>((int) count, pageCount, list));
    }

    @Override
    public ResultVO listBrands(String keyword) {
        keyword = "%" + keyword + "%";
        return new ResultVO(ResStatus.OK, "success", productMapper.selectBrandByKeyword(keyword));
    }
}
