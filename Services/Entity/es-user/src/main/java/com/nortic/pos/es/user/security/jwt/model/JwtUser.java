package com.nortic.pos.es.user.security.jwt.model;

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
                && permissions.contains(permission);
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