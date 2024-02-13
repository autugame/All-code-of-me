package com.qfedu;

import com.qfedu.fmmall.ApiApplication;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.entity.Product4ES;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.entity.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
public class ImportProductInfo {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void CreateIndex() {
        elasticsearchRestTemplate.indexOps(IndexCoordinates.of("products")).create();
    }

    @Test
    public void ImportData() {
        List<ProductVO> productVOS = productMapper.selectProducts();
        for (ProductVO productVO : productVOS) {
            String productId = productVO.getProductId();
            String productName = productVO.getProductName();
            int soldNum = productVO.getSoldNum();
            List<ProductSku> skus = productVO.getSkus();
            String skuName = skus.isEmpty() ? "" : skus.get(0).getSkuName();
            String skuImg = skus.isEmpty() ? "" : skus.get(0).getSkuImg();
            int sellPrice = skus.isEmpty() ? 0 : skus.get(0).getSellPrice();
            Product4ES product4ES = new Product4ES(productId, productName, skuImg, soldNum, skuName, sellPrice);
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(productId)
                            .withObject(product4ES)
                                    .build();
            String products = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("products"));
            System.out.println(products);
        }
    }

}
