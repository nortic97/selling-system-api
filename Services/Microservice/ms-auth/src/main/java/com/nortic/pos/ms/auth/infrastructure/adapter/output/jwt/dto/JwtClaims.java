package com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.dto;

import lombok.*;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtClaims {
    private String type;
    private List<String> permissions;
}
