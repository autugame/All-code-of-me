package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供首页所有所需要的接口", tags = "首页接口")
public class IndexController {
    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @ApiOperation("轮播图图片路径接口")
    @GetMapping("/img")
    public ResultVO queryIndexImages() {
        return indexImgService.queryIndexImages();
    }

    @ApiOperation("商品分类查询接口")
    @GetMapping("/categories")
    public ResultVO queryCategories() {
        return categoryService.listCategories();
    }

    @ApiOperation("新品推荐商品查询接口")
    @GetMapping("/list-recommend")
    public ResultVO queryRecommendProducts() {
        return productService.listRecommendProduct();
    }

    @ApiOperation("分类推荐商品查询接口")
    @GetMapping("/list-categories-recommend")
    public ResultVO queryRecommendProductsByCategory() {
        return categoryService.listFirstLevelCategories();
    }
}
