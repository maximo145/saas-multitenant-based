package com.decode.saassharedschema.security.service.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.decode.saassharedschema.security.domain.entities.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsDto extends User implements UserDetails {

    private final User user;

    public UserDetailsDto(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public Boolean getActive() {
        return user.getActive();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() { return user;}










}
