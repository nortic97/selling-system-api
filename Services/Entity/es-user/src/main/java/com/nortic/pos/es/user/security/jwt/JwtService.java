package com.nortic.pos.es.user.security.jwt;

import com.nortic.pos.es.user.security.jwt.model.JwtUser;

public interface JwtService {

    JwtUser validateToken(String token);

}
