package com.decode.saassharedschema.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.decode.saassharedschema.security.service.dto.UserDetailsDto;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {
    private static String jwtSecret;
    private  static long jwtExpirationInMs;

    public JwtTokenProvider(@Value("${app.jwt.secret}") String jwtSecret, @Value("${app.jwt.expiration}") long jwtExpirationInMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInMs = jwtExpirationInMs;
    }
    public static String generateToken(UserDetailsDto userDetailsDto) {
        Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);
        Map<String, Object> dataAditional = new HashMap<>();
        dataAditional.put("status", userDetailsDto.getActive());
        return Jwts.builder()
                .setSubject(userDetailsDto.getUserName())
                .addClaims(dataAditional)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .setSubject(userDetailsDto.getUser().getTenant() != null ? userDetailsDto.getUser().getTenant().getSlug() : "")
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAutentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String user = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }


    public static long getExpiryDuration() {
        return jwtExpirationInMs;
    }

    // Validar el token de acceso
    public static boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e){
            //log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }
    }

    public static String getTenantOfToken(String token) {
        String sub = null;
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            sub = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }
}
