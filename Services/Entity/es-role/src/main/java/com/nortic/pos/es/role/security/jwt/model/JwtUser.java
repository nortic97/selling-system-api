package com.nortic.pos.es.role.security.jwt.model;

import com.nortic.pos.es.role.security.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
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
                || permissions.contains(SecurityConstants.PERMISSION_ROLE_ALL)
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
