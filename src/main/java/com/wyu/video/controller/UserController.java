package com.wyu.video.controller;

import com.wyu.video.controller.support.UserSupport;
import com.wyu.video.dto.UserLoginDTO;
import com.wyu.video.dto.UserRegisterDTO;
import com.wyu.video.dto.UserUpdateDTO;
import com.wyu.video.service.IUserService;
import com.wyu.video.util.RSAUtil;
import com.wyu.video.vo.ResultVo;
import com.wyu.video.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zwx
 * @date 2022-06-18 19:01
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserSupport userSupport;


    @GetMapping("/rsa-pks")
    public ResultVo<String> getPublicKey(){
        String publicKey = RSAUtil.getPublicKeyStr();
        return ResultVo.success(publicKey);
    }

    @GetMapping("/users-info")
    public ResultVo<UserVo> getUserInfo(){
        Long userId = userSupport.getCurrentUserId();
        UserVo userVo = userService.getUserInfo(userId);
        return ResultVo.success(userVo);
    }

    @PostMapping("/users")
    public ResultVo<String> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        userService.register(userRegisterDTO);
        return ResultVo.success();
    }

    @PostMapping("/users-tokens")
    public ResultVo<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws Exception{
        String token = userService.login(userLoginDTO);
        return ResultVo.success(token);
    }

    @PutMapping("/users-info")
    public ResultVo<String> updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO) {
        Long userId = userSupport.getCurrentUserId(); // 一般用户id是从header的token获取 而不是从参数，防止伪造id
        userUpdateDTO.setId(userId);
        userService.updateUserInfo(userUpdateDTO);
        return ResultVo.success();
    }

}
