package com.qfedu.fmmall.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qfedu.fmmall.dao.IndexImgMapper;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class IndexImgServiceImpl implements IndexImgService {
    @Autowired
    private IndexImgMapper indexImgMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Gson gson = new Gson();

    @Override
    public ResultVO queryIndexImages() {
        List<IndexImg> indexImages = null;
        //Cache the carousel information.
        String imgStr = stringRedisTemplate.boundValueOps("indexImages").get();
        if (imgStr == null) {
            synchronized(this) {
                String s = stringRedisTemplate.boundValueOps("indexImages").get();
                if (s == null) {
                    //Query the database.
                    indexImages = indexImgMapper.listIndexImages();
                    if (indexImages == null) {
                        List<IndexImg> arr = new ArrayList<>();
                        stringRedisTemplate.boundValueOps("indexImages").set(gson.toJson(arr), 1, TimeUnit.MINUTES);
                    } else {
                        //Write data to redis.
                        stringRedisTemplate.boundValueOps("indexImages").set(gson.toJson(indexImages));
                        //Set the expiration time to 1 day.
                        stringRedisTemplate.boundValueOps("indexImages").expire(1, TimeUnit.DAYS);
                    }
                } else {
                    //Got a picture of carousel from the redis.
                    indexImages = gson.fromJson(s, new TypeToken<List<IndexImg>>() {}.getType());
                }
            }
        } else {
            //Got a picture of carousel from the redis.
            indexImages = gson.fromJson(imgStr, new TypeToken<List<IndexImg>>() {}.getType());
        }
        //Return data.

        if (indexImages != null) {
            return new ResultVO(ResStatus.OK, "success", indexImages);
        } else {
            return new ResultVO(ResStatus.OK, "success");
        }
    }
}
