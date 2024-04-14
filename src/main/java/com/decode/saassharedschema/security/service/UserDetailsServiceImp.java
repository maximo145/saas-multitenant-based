package com.decode.saassharedschema.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.decode.saassharedschema.security.domain.entities.User;
import com.decode.saassharedschema.security.domain.persistence.IUserRepository;
import com.decode.saassharedschema.security.service.dto.UserDetailsDto;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImp  implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = null;
        try {

            user = this.userRepository.findByUserName(username);

            if (user.isEmpty()) {
                log.error("El usuario con nombre " + username + " no existe.");
                throw new UsernameNotFoundException("El usuario con nombre " + username + " no existe.");
            }
        } catch (UsernameNotFoundException ex){
            log.error("Error " + ex);
        }

        return new UserDetailsDto(user.get());
    }
}
