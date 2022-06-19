package com.wyu.video.service.impl;

import com.wyu.video.constant.UserConstant;
import com.wyu.video.dto.UserLoginDTO;
import com.wyu.video.dto.UserUpdateDTO;
import com.wyu.video.enums.ResultEnum;
import com.wyu.video.exception.ConditionException;
import com.wyu.video.dto.UserRegisterDTO;
import com.wyu.video.mapper.UserInfoMapper;
import com.wyu.video.mapper.UserMapper;
import com.wyu.video.pojo.User;
import com.wyu.video.pojo.UserInfo;
import com.wyu.video.service.IUserService;
import com.wyu.video.util.MD5Util;
import com.wyu.video.util.RSAUtil;
import com.wyu.video.util.TokenUtil;
import com.wyu.video.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zwx
 * @date 2022-06-18 18:59
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        String phone = userRegisterDTO.getPhone();
        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException(ResultEnum.REGISTERED_ERROR.getCode(), ResultEnum.REGISTERED_ERROR.getDesc());
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = userRegisterDTO.getPassword();

        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException(ResultEnum.DECODE_ERROR.getCode(), ResultEnum.DECODE_ERROR.getDesc());
        }
        // 解密后再用MD5加密(加盐)存入数据库
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        userMapper.insertSelective(user);

        //添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userInfoMapper.insertSelective(userInfo);
        //添加用户默认权限角色
        //userAuthService.addUserDefaultRole(user.getId());
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) throws Exception{
        String phone = userLoginDTO.getPhone();
        User dbUser = this.getUserByPhone(phone);
        if (dbUser == null) {
            throw new ConditionException(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getDesc());
        }

        String password = userLoginDTO.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException(ResultEnum.DECODE_ERROR.getCode(), ResultEnum.DECODE_ERROR.getDesc());
        }

        // 判断密码
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if (!md5Password.equals(dbUser.getPassword())) {
            throw new ConditionException(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getDesc());
        }


        return TokenUtil.generateToken(dbUser.getId());
    }

    @Override
    public UserVo getUserInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserInfo userInfo = userInfoMapper.selectByUserId(userId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);
        userVo.setPhone(user.getPhone());
        userVo.setEmail(user.getEmail());
        userVo.setId(user.getId());

        return userVo;
    }

    @Override
    public void updateUserInfo(UserUpdateDTO userUpdateDTO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userUpdateDTO, userInfo);
        userInfo.setUserId(userUpdateDTO.getId());
        userInfoMapper.updateUserInfo(userInfo);
    }
}
