package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口", tags = "商品管理")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/detail-info/{pid}")
    @ApiOperation("商品详细查询接口")
    public ResultVO getProductBasicInfo(@PathVariable("pid") String pid){
        return productService.getProductBasicInfo(pid);
    }

    @GetMapping("/detail-params/{pid}")
    @ApiOperation("商品参数信息查询接口")
    public ResultVO getProductParamsById(@PathVariable("pid") String pid){
        return productService.getProductParamsById(pid);
    }

    @GetMapping("/detail-comments/{pid}")
    @ApiOperation("商品评论查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "pageNum", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "integer", name = "limit", value = "分页条数", required = true)
    })
    public ResultVO getProductComments(@PathVariable("pid") String pid, int pageNum, int limit){
        return productService.listCommentsByProductId(pid, pageNum, limit);
    }

    @GetMapping("/detail-comments-count/{pid}")
    @ApiOperation("商品评价统计查询接口")
    public ResultVO getProductCommentsCount(@PathVariable("pid") String pid){
        return productService.getCommentsCountByProductId(pid);
    }

    @GetMapping("/list-by-cid/{cid}")
    @ApiOperation("根据内别查询商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "pageNum", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "integer", name = "limit", value = "分页条数", required = true)
    })
    public ResultVO getProductsByCategoryId(@PathVariable("cid") int cid, int pageNum, int limit){
        return productService.getProductsByCategoryId(cid, pageNum, limit);
    }

    @GetMapping("/list-brands/{cid}")
    @ApiOperation("根据类别查询商品品牌")
    public ResultVO getBrandsByCategoryId(@PathVariable("cid") int cid){
        return productService.listBrands(cid);
    }

    @GetMapping("/list-by-keyword")
    @ApiOperation("根据keyword模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "keyword", value = "搜索关键字", required = true),
            @ApiImplicitParam(dataType = "integer", name = "pageNum", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "integer", name = "limit", value = "分页条数", required = true)
    })
    public ResultVO searchProduct(String keyword, int pageNum, int limit){
        return productService.searchProduct(keyword, pageNum, limit);
    }

    @GetMapping("/list-brands")
    @ApiOperation("根据关键字查询商品品牌")
    @ApiImplicitParam(dataType = "String", name = "keyword", value = "搜索关键字", required = true)
    public ResultVO getBrandsByKeyword(String keyword){
        return productService.listBrands(keyword);
    }
}
