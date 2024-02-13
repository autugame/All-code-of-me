package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.Category;
import com.qfedu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends GeneralDao<Category> {
    List<CategoryVO> selectAllCategories();

    //Query level 1 categories.
    List<CategoryVO> selectFirstLevelCategories();
}