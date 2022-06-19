package com.wyu.video.service;

import com.wyu.video.dto.UserUpdateDTO;
import com.wyu.video.pojo.User;
import com.wyu.video.dto.UserLoginDTO;
import com.wyu.video.dto.UserRegisterDTO;
import com.wyu.video.pojo.UserInfo;
import com.wyu.video.vo.UserVo;

/**
 * @author zwx
 * @date 2022-06-19 12:57
 */
public interface IUserService {

     void register(UserRegisterDTO userRegisterDTO);

    User getUserByPhone(String phone);

    String login(UserLoginDTO userLoginDTO) throws Exception;

    UserVo getUserInfo(Long userId);

    void updateUserInfo(UserUpdateDTO userUpdateDTO);
}
