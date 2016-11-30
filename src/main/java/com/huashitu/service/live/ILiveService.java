package com.huashitu.service.live;

import com.huashitu.config.BaseService;
import com.huashitu.domain.Live;

import java.util.List;

/**
 * Created by levy on 2016/11/22.
 */
public interface ILiveService extends BaseService<Live>  {

    public List<Live> selectOnlineLive(int onlineStatus);

}
