package com.wyu.video.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author zwx
 * @date 2022-06-18 0:34
 */
@Mapper
public interface DemoMapper {
    public Long query(Long id);
}
