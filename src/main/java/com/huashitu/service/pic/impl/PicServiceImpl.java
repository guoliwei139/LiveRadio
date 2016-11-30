package com.huashitu.service.pic.impl;

import com.huashitu.common.system.SysStatic;
import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.Pic;
import com.huashitu.mapper.PicMapper;
import com.huashitu.service.pic.IPicService;
import com.huashitu.util.BaseMapper;
import com.huashitu.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by levy on 2016/11/9.
 */
@Service
public class PicServiceImpl extends BaseServiceImpl<Pic> implements IPicService {

    @Autowired
    private PicMapper picMapper;

    @Override
    protected BaseMapper<Pic> getBaseMapper() {
        return this.picMapper;
    }

    @Override
    public Long saveImg( MultipartFile file, HttpServletRequest request) {
        Long id = null;
        Pic p = new Pic();
        if (file != null && !file.isEmpty()) {
            String path = "layout/upload/img/head";
            String imgName = FileUploadUtil.uploadImage(file, path, request);
            System.out.println("----上传头像成功,头像地址--" + path + imgName);
            p.setPath(path);
            p.setPicName(imgName);
            p.setDeleteFlag(SysStatic.NORMALDELFLAG);
            p.setCreateTime(new Date());
            insertPic(p);
        }
    return p.getId();
    }

    @Override
    public Long saveUrlImg(String imgUrl, HttpServletRequest request) {
        Long id = null;
        Pic p = new Pic();
        if (imgUrl!=null){
            String path = "layout/upload/img/head";
            String imgName = FileUploadUtil.uploadUrlImage(imgUrl,path,request);
            System.out.println("----上传头像成功,头像地址--" + path + imgName);
            p.setPath(path);
            p.setPicName(imgName);
            p.setDeleteFlag(SysStatic.NORMALDELFLAG);
            p.setCreateTime(new Date());
            insertPic(p);
        }
        return p.getId();
    }


    @Override
    public Long insertPic(Pic p) {
        return this.picMapper.insertPic(p);
    }


}