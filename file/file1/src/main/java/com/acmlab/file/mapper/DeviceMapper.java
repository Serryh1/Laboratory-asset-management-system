package com.acmlab.file.mapper;

import com.acmlab.file.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    void add(@Param("device") Device device);
    void update(@Param("device") Device device);
    void delete(@Param("sid") Integer sid);
    List<Device> select(@Param("device") Device device);
}
