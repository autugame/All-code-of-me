package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface CategoryService {
    public ResultVO listCategories();
    ResultVO listFirstLevelCategories();
}
