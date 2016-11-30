package com.huashitu.mapper;

import com.huashitu.domain.Device;
import com.huashitu.util.BaseMapper;

public interface DeviceMapper extends BaseMapper<Device> {
    Integer saveOrUpdateDevice(Device device);
    Integer isExistDeviceByUserId(Long userId);
    Integer updateDeviceByUserId(Device device);
}