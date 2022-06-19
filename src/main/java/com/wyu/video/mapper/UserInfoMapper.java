package com.wyu.video.mapper;

import com.wyu.video.dto.UserUpdateDTO;
import com.wyu.video.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);
    UserInfo selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateUserInfo(UserInfo userInfo);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}