package com.nortic.pos.ms.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    private String sub;
    private String iss;
    private String type;
    private String storeId;
    private String branchId;
    private String role;
    private List<String> permissions;
    private Instant iat;
    private Instant exp;
}
