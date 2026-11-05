package com.nortic.pos.es.employee.security.jwt.model;

import com.nortic.pos.es.employee.security.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtEmployee {

    private String userId;

    private String storeId;

    private String branchId;

    private String tokenType;

    private List<String> permissions;

    public boolean hasPermission(String permission) {

        return permissions != null
                && (permissions.contains(permission)
                || permissions.contains(SecurityConstants.PERMISSION_EMPLOYEE_ALL)
                || permissions.contains(SecurityConstants.PERMISSION_SYSTEM_ALL));
    }

    public void validatePermission(String permission) {

        if (!hasPermission(permission)) {

            throw new SecurityException(
                    String.format(
                            "Missing permission: %s",
                            permission
                    )
            );
        }
    }
}