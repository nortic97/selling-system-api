package com.nortic.pos.es.employeeworkspace.security.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static Object getJwtUser() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        HttpServletRequest request =
                attributes.getRequest();

        return request.getAttribute(
                "jwtUser"
        );
    }
}
