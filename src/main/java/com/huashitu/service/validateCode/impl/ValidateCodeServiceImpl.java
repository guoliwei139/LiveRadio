package com.huashitu.service.validateCode.impl;

import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.ValidateCode;
import com.huashitu.mapper.ValidateCodeMapper;
import com.huashitu.service.validateCode.IValidateCodeService;
import com.huashitu.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levy on 2016/11/10.
 */
@Service
public class ValidateCodeServiceImpl extends BaseServiceImpl<ValidateCode> implements IValidateCodeService {
    @Autowired
    private ValidateCodeMapper validateCodeMapper;

    @Override
    protected BaseMapper<ValidateCode> getBaseMapper() {
        return this.validateCodeMapper;
    }

}
