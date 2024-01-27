package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.*;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public ResultVO listRecommendProduct() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        return new ResultVO(ResStatus.OK, "success", productVOS);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO getProductBasicInfo(String productId) {
        //Basic product information.
        Example example = new Example(Product.class);
        example.createCriteria()
                .andEqualTo("productId", productId)
                .andEqualTo("productStatus", 1);

        List<Product> products = productMapper.selectByExample(example);

        if (!products.isEmpty()) {
            //Product picture.
            Example example1 = new Example(ProductImg.class);
            example1.createCriteria()
                    .andEqualTo("itemId", productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
            //Product package.
            Example example2 = new Example(ProductSku.class);
            example2.createCriteria()
                    .andEqualTo("productId", productId)
                    .andEqualTo("status", 1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);

            HashMap<String, Object> basicInfo = new HashMap<>();
            basicInfo.put("product", products.get(0));
            basicInfo.put("productImgs", productImgs);
            basicInfo.put("productSkus", productSkus);
            return new ResultVO(ResStatus.OK, "success", basicInfo);
        } else {
            return new ResultVO(ResStatus.NO, "error");
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
        keyword = "%" + keyword + "%";
        int start = (pageNum - 1) * limit;
        List<ProductVO> productVOS = productMapper.selectProductByKeyword(keyword, start, limit);
        //Query the total number of record.
        Example example = new Example(Product.class);
        example.createCriteria()
                .andLike("productName", keyword);
        int count = productMapper.selectCountByExample(example);
        //Calculate total pages.
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;
        return new ResultVO(ResStatus.OK, "success", new PageHelper<ProductVO>(count, pageCount, productVOS));
    }

    @Override
    public ResultVO listBrands(String keyword) {
        keyword = "%" + keyword + "%";
        return new ResultVO(ResStatus.OK, "success", productMapper.selectBrandByKeyword(keyword));
    }
}
