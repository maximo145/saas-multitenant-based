package com.decode.saassharedschema.security.service.resources;

import lombok.*;
import com.decode.saassharedschema.security.service.dto.UserDto;

import java.util.List;

@Data
@NoArgsConstructor
public class UserListResponse {
    private List<UserDto> users = null;

    public UserListResponse user(List<UserDto> users) {
        this.users = users;
        return this;
    }
}
