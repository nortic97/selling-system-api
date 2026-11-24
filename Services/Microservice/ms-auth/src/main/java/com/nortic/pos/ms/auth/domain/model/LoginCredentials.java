package com.nortic.pos.ms.auth.domain.model;

import com.nortic.pos.ms.auth.domain.model.enums.AccessTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentials {
    private String storeId;
    private String username;
    private String password;
    private AccessTypeEnum accessTypeEnum;
}
