package com.huashitu.service.device;

import com.huashitu.config.BaseService;
import com.huashitu.domain.Device;

/**
 * Created by levy on 2016/11/8.
 */
public interface IDeviceService extends BaseService<Device>{
    /**1：接收推送，默认接收*/
    public static final Integer IS_RECEIVE = 1;
    /**2：不接受推送*/
    public static final Integer IS_NO_RECEIVE = 2;

    Integer saveOrUpdateDevice(Device device);
    Integer isExistDeviceByUserId(Long userId);
    Integer updateDeviceByUserId(Device device);

}
