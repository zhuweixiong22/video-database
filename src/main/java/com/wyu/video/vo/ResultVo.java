package com.wyu.video.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wyu.video.enums.ResultEnum;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * @author zwx
 * @date 2022-06-18 16:20
 */
public class ResultVo<T> {
    private Integer code;

    private  String msg;

    private T data;


    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVo<T> successByMsg(String msg) {
        return new ResultVo<T>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
    }

    public static <T> ResultVo<T> success() {
        return new ResultVo<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }


    public static <T> ResultVo<T> error(Integer code, String msg) {
        return new ResultVo<>(code, msg);
    }

    public static <T> ResultVo<T> error(ResultEnum responseEnum, BindingResult bindingResult) {
        //为了返回更详细的信息 code以枚举为准 msg以参数为准
        return new ResultVo<T>(responseEnum.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " "
                        + bindingResult.getFieldError().getDefaultMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
