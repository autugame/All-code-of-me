package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.general.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDao<IndexImg> {
    //Example Query information about a carousel chart.
    public List<IndexImg> listIndexImages();
}