package com.wyu.video.handler;

import com.wyu.video.enums.ResultEnum;
import com.wyu.video.vo.ResultVo;
import com.wyu.video.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-06-18 16:41
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // 设置优先级最高 全局处理器
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultVo<String> commonExceptionHandler(RuntimeException e) {
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException) {
            Integer errorCode = ((ConditionException) e).getCode();
            return ResultVo.error(errorCode, errorMsg);
        }
        // TODO
        e.printStackTrace();
        return ResultVo.error(ResultEnum.ERROR.getCode(), errorMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVo methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        //getDefaultMessage()获取到的是@NotBlank的message值
        BindingResult bindingResult = e.getBindingResult();
        return ResultVo.error(ResultEnum.PARAM_ERROR.getCode(), Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}
