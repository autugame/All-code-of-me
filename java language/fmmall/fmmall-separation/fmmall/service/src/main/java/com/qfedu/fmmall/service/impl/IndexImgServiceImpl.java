package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.IndexImgMapper;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexImgServiceImpl implements IndexImgService {
    @Autowired
    private IndexImgMapper indexImgMapper;

    @Override
    public ResultVO queryIndexImages() {
        List<IndexImg> indexImages = indexImgMapper.listIndexImages();
        if (indexImages.isEmpty()) {
            return new ResultVO(ResStatus.NO, "success");
        } else {
            return new ResultVO(ResStatus.OK, "success", indexImages);
        }
    }
}
