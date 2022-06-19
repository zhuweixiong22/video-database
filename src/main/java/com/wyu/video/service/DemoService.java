package com.wyu.video.service;

import com.wyu.video.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zwx
 * @date 2022-06-18 0:43
 */
@Service
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public Long query(Long id) {
        return demoMapper.query(id);
    }
}
