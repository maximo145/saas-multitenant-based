package com.decode.saassharedschema.security.mapping;

import org.modelmapper.ModelMapper;
import com.decode.saassharedschema.security.domain.entities.User;
import com.decode.saassharedschema.security.service.dto.UserDto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapping extends ModelMapper implements Serializable {

    public UserMapping() {
        super();
    }

    public List<UserDto> modelList(List<User> modelList) {
        return modelList.stream().map(item -> this.map(item, UserDto.class))
                .collect(Collectors.toList());
    }

    public User model(UserDto userDto) {
        return this.map(userDto, User.class);
    }

    public UserDto modelDto(User userDto) {
        return this.map(userDto, UserDto.class);
    }
}
