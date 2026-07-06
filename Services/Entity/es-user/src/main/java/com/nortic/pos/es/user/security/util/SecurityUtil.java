package com.nortic.pos.es.user.security.util;

import com.nortic.pos.es.user.security.jwt.model.JwtUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static JwtUser getJwtUser() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        HttpServletRequest request =
                attributes.getRequest();

        return (JwtUser) request.getAttribute(
                "jwtUser"
        );
    }
}
