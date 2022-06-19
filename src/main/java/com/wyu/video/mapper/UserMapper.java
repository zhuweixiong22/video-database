package com.wyu.video.mapper;

import com.wyu.video.pojo.User;

public interface UserMapper {

    User getUserByPhone(String phone);
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}