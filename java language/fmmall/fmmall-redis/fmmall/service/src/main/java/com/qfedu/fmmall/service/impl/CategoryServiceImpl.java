package com.qfedu.fmmall.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.entity.CategoryVO;
import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Gson gson = new Gson();

    @Override
    public ResultVO listCategories() {
        List<CategoryVO> categoryVOS = null;

        String categories = stringRedisTemplate.boundValueOps("categories").get();
        //Judgment
        if (categories != null) {
            categoryVOS = gson.fromJson(categories, new TypeToken<List<CategoryVO>>() {}.getType());
        } else {
            //If there is no data in redis, then query the database.
            categoryVOS = categoryMapper.selectAllCategories();
            stringRedisTemplate.boundValueOps("categories").set(gson.toJson(categoryVOS), 1, TimeUnit.DAYS);
        }
        return new ResultVO(ResStatus.OK, "success", categoryVOS);
    }

    @Override
    public ResultVO listFirstLevelCategories() {
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        return new ResultVO(ResStatus.OK, "success", categoryVOS);
    }
}
