package com.nortic.pos.es.user.security.jwt.model;

import lombok.Getter;

@Getter
public final class Permission {

    public static final String USER_READ =
            "user.read";

    public static final String USER_CREATE =
            "user.create";

    public static final String USER_UPDATE =
            "user.update";

    public static final String USER_DELETE =
            "user.delete";
}