package com.decode.saassharedschema.security.service.dto;

import com.decode.saassharedschema.security.domain.entities.Tenant;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private Boolean active;
    private Tenant tenant;
}
