package com.huashitu.service.indexPage;


import com.huashitu.config.BaseService;
import com.huashitu.domain.FansAttention;
import com.huashitu.domain.User;

import java.util.List;

/**
 * Created by levy on 2016/11/21.
 */
public interface IFansAttentionService extends BaseService<FansAttention> {

    public List<User> selectIdolByUserId(Long userId);
}
