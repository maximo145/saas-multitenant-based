package com.decode.saassharedschema.security.service.dto;

import lombok.Data;

@Data
public class TenantDto {
    private long id;
    private String slug;
    private String name;
}
