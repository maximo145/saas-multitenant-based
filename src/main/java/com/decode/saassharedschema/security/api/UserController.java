package com.decode.saassharedschema.security.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.decode.saassharedschema.util.ResponseDto;
import com.decode.saassharedschema.security.domain.service.IUserService;
import com.decode.saassharedschema.security.service.dto.UserDto;
import com.decode.saassharedschema.security.service.resources.UserListResponse;
import com.decode.saassharedschema.security.service.resources.UserResponse;

import java.io.IOException;

@RestController
@RequestMapping(value = "security/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseDto<UserListResponse> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("{id}")
    public ResponseDto<UserResponse> retrieveUser(@PathVariable Long id) {
        return userService.retrieveUser(id);
    }

    @PostMapping
    public ResponseDto<UserResponse> registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PutMapping
    public ResponseDto<UserResponse> updateUser(@RequestBody UserDto userDto) throws IOException {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<UserResponse> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
