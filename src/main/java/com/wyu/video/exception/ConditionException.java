package com.wyu.video.exception;

import com.wyu.video.enums.ResultEnum;

/**
 * @author zwx
 * @date 2022-06-18 16:43
 */
public class ConditionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;


    public ConditionException(Integer code, String desc) {
        super(desc);
        this.code = code;
    }

    public ConditionException(ResultEnum responseEnum) {
        super(responseEnum.getDesc());
        this.code = responseEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
