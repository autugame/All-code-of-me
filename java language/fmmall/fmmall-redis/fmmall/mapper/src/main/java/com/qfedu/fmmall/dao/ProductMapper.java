package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.Product;
import com.qfedu.fmmall.entity.ProductVO;
import com.qfedu.fmmall.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDao<Product> {
    List<ProductVO> selectRecommendProducts();
    //Top 6 best-selling items.
    List<ProductVO> selectTop6ByCategory(int cid);

    /**
     * Query product information based on the three-level category id.
     * @param cid three-level category id
     * @param start initial index
     * @param limit count
     * @return productVO list
     */
    List<ProductVO> selectProductByCategoryId(@Param("cid") int cid, @Param("start") int start, @Param("limit") int limit);

    List<String> selectBrandByCategoryId(int cid);

    List<ProductVO> selectProductByKeyword(@Param("keyword") String keyword, @Param("start") int start, @Param("limit") int limit);

    List<String> selectBrandByKeyword(String keyword);
}