package com.decode.saassharedschema.security.domain.service;

import com.decode.saassharedschema.util.ResponseDto;
import com.decode.saassharedschema.security.service.dto.UserDto;
import com.decode.saassharedschema.security.service.resources.UserListResponse;
import com.decode.saassharedschema.security.service.resources.UserResponse;

public interface IUserService {

    ResponseDto<UserListResponse> listUsers();

    ResponseDto<UserResponse> retrieveUser(Long id);

    ResponseDto<UserResponse> registerUser(UserDto userDto);

    ResponseDto<UserResponse> updateUser(UserDto userDto);

    ResponseDto deleteUser(Long id);
}
