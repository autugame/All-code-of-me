package com.qfedu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

/**
 * 表名：category
 * 表注释：商品分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryVO {
    /**
     * 主键 分类id主键
     * -- GETTER --
     *  获取主键 分类id主键
     *

     */
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 分类名称 分类名称
     * -- GETTER --
     *  获取分类名称 分类名称
     *

     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 分类层级 分类得类型，
     1:一级大分类
     2:二级分类
     3:三级小分类
     * -- GETTER --
     *  获取分类层级 分类得类型，
     * 1:一级大分类
     * 2:二级分类
     * 3:三级小分类
     *

     */
    @Column(name = "category_level")
    private Integer categoryLevel;

    /**
     * 父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级
     * -- GETTER --
     *  获取父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级
     *

     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 图标 logo
     * -- GETTER --
     *  获取图标 logo
     *

     */
    @Column(name = "category_icon")
    private String categoryIcon;

    /**
     * 口号
     * -- GETTER --
     *  获取口号
     *

     */
    @Column(name = "category_slogan")
    private String categorySlogan;

    /**
     * 分类图
     * -- GETTER --
     *  获取分类图
     *

     */
    @Column(name = "category_pic")
    private String categoryPic;

    /**
     * 背景颜色
     * -- GETTER --
     *  获取背景颜色
     *

     */
    @Column(name = "category_bg_color")
    private String categoryBgColor;
    //Display category.
    private List<CategoryVO> categories;
    //Classified product recommendation.
    private List<ProductVO> products;
}
