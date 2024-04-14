package com.decode.saassharedschema.security.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseTokenDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiryDuration;
    private UserDto user;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiryDuration(Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    //--------------------------------------------------------


    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiryDuration() {
        return expiryDuration;
    }

    public UserDto getUser() {
        return user;
    }

}
