package com.decode.saassharedschema.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.decode.saassharedschema.security.provider.JwtTokenProvider;
import com.decode.saassharedschema.security.service.dto.ResponseTokenDto;
import com.decode.saassharedschema.security.service.dto.UserDetailsDto;
import com.decode.saassharedschema.security.service.dto.UserDto;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UserDto userDto = new UserDto();
        Authentication authentication = null;

        try {
            userDto = new ObjectMapper().readValue(request.getReader(), UserDto.class);
        } catch (IOException e) {

        }

        UsernamePasswordAuthenticationToken userNamePAT = new UsernamePasswordAuthenticationToken(
                userDto.getUserName(),
                userDto.getPassword(),
                Collections.emptyList()
        );

        authentication = getAuthenticationManager().authenticate(userNamePAT);

        return authentication;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsDto userDetailsDto = (UserDetailsDto) authResult.getPrincipal();
        String token = JwtTokenProvider.generateToken(userDetailsDto);
        ResponseTokenDto responseTokenDto = new ResponseTokenDto();
        responseTokenDto.setAccessToken(token);
        responseTokenDto.setExpiryDuration(JwtTokenProvider.getExpiryDuration());
        UserDto userDto = new UserDto();
        userDto.setId(userDetailsDto.getUser().getId());
        userDto.setUserName(userDetailsDto.getUser().getUserName());
        userDto.setTenant(userDetailsDto.getUser().getTenant());
        responseTokenDto.setUser(userDto);
        ObjectMapper objectMapper= new ObjectMapper();
        String responseTokenDtoJson = objectMapper.writeValueAsString(responseTokenDto);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseTokenDtoJson);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
