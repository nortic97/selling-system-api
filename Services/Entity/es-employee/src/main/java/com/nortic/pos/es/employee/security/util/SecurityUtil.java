package com.nortic.pos.es.employee.security.util;

import com.nortic.pos.es.employee.security.jwt.model.JwtEmployee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static JwtEmployee getJwtEmployee() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        HttpServletRequest request =
                attributes.getRequest();

        return (JwtEmployee) request.getAttribute(
                "jwtEmployee"
        );
    }
}
