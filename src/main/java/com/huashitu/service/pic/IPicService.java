package com.huashitu.service.pic;

import com.huashitu.config.BaseService;
import com.huashitu.domain.Pic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by levy on 2016/11/9.
 */
public interface IPicService extends BaseService<Pic> {

    public Long saveImg(MultipartFile file, HttpServletRequest request);
    public Long saveUrlImg(String url, HttpServletRequest request);
    public Long insertPic(Pic p);
}
