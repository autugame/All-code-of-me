package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface ProductService {
    public ResultVO listRecommendProduct();

    public ResultVO getProductBasicInfo(String productId);

    public ResultVO getProductParamsById(String productId);

    /**
     * Query reviews based on the product id.
     * @param productId Product id.
     * @param pageNum Page number.
     * @param limit Page size.
     * @return
     */
    public ResultVO listCommentsByProductId(String productId, int pageNum, int limit);

    /**
     * Collect product evaluation information based on the current product id.
     * @param productId
     * @return
     */
    public ResultVO getCommentsCountByProductId(String productId);

    public ResultVO getProductsByCategoryId(int categoryId, int pageNum, int limit);

    public ResultVO listBrands(int categoryId);

    public ResultVO searchProduct(String keyword, int pageNum, int limit);

    public ResultVO listBrands(String keyword);
}
