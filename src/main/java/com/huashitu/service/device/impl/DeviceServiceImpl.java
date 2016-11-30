package com.huashitu.service.device.impl;

import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.Device;
import com.huashitu.mapper.DeviceMapper;
import com.huashitu.service.device.IDeviceService;
import com.huashitu.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levy on 2016/11/8.
 */
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Integer saveOrUpdateDevice(Device device) {
        return this.deviceMapper.saveOrUpdateDevice(device);
    }

    @Override
    public Integer isExistDeviceByUserId(Long userId) {
        return this.deviceMapper.isExistDeviceByUserId(userId);
    }

    @Override
    public Integer updateDeviceByUserId(Device device) {
        return this.deviceMapper.updateDeviceByUserId(device);
    }


    @Override
    protected BaseMapper<Device> getBaseMapper() {
        return this.deviceMapper;
    }
}
