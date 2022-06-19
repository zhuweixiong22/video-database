package com.wyu.video.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zwx
 * @date 2022-06-19 15:41
 */

public class UserUpdateDTO {
    private Long id;

    // TODO 手机和邮箱修改
    /*@NotBlank(message = "手机号码不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    private String email;
    */

    @NotBlank(message = "昵称不能为空")
    private String nick;

    @NotBlank(message = "用户头像不能为空")
    private String avatar;

    @NotBlank(message = "性别不能为空")
    private String gender;

    @NotBlank(message = "生日不能为空")
    private String birth;

    private String sign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
