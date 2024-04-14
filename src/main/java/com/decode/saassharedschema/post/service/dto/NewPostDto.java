package com.decode.saassharedschema.post.service.dto;

import com.decode.saassharedschema.security.service.dto.TenantDto;
import com.decode.saassharedschema.security.service.dto.UserDto;
import lombok.Data;

@Data
public class NewPostDto {
    private long id;
    private String text;
    private TenantDto tenant;
    private UserDto author;
}
