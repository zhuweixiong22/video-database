package com.wyu.video.controller.support;

import com.wyu.video.exception.ConditionException;
import com.wyu.video.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zwx
 * @date 2022-06-19 14:22
 */
@Component
public class UserSupport {
    /**
     * 统一功能
     * 从请求头里拿token解析出userId
     *
     * @return
     */
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);

        if (userId <= 0) {
            throw new ConditionException(500, "非法用户");
        }
        return userId;
    }
}
