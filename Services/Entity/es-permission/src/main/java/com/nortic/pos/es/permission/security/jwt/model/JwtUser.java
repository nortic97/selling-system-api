package com.nortic.pos.es.permission.security.jwt.model;

import com.nortic.pos.es.permission.security.SecurityConstants;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {

    private String userId;

    private String storeId;

    private String branchId;

    private String tokenType;

    private List<String> permissions;

    public boolean hasPermission(String permission) {

        return permissions != null
                && (permissions.contains(permission)
                || permissions.contains(SecurityConstants.PERMISSION_PERMISSION_ALL)
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
