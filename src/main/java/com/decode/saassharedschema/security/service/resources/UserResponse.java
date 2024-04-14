package com.decode.saassharedschema.security.service.resources;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.decode.saassharedschema.security.service.dto.UserDto;

@Data
@NoArgsConstructor
public class UserResponse {

    private UserDto user = null;

    public UserResponse user(UserDto userDto) {
        this.user = userDto;
        return this;
    }
}
