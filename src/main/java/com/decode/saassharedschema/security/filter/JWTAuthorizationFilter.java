package com.decode.saassharedschema.security.filter;

import com.decode.saassharedschema.security.domain.entities.Tenant;
import com.decode.saassharedschema.security.domain.persistence.TenantRepository;
import com.decode.saassharedschema.security.tenants.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.decode.saassharedschema.security.provider.JwtTokenProvider;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final TenantRepository tenantRepository;
    public JWTAuthorizationFilter(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String beareToken = request.getHeader("Authorization");
            String url = request.getRequestURI().substring(request.getContextPath().length());
            if (url.equals("/security/user/login")) {
                filterChain.doFilter(request, response);
                return;
            }
            if(beareToken != null && beareToken.startsWith("Bearer ")) {
                String token = beareToken.replace("Bearer ", "");
                if(JwtTokenProvider.isTokenValid(token)){
                    UsernamePasswordAuthenticationToken usernamePAT = JwtTokenProvider.getAutentication(token);
                    SecurityContextHolder.getContext().setAuthentication(usernamePAT);
                    var tenant = JwtTokenProvider.getTenantOfToken(token);
                    var tenantId = tenantRepository.findBySlug(tenant).map(Tenant::getId).orElse(null);
                    var subdomain = getTenant(request);
                    if (Objects.equals(tenant, "") && subdomain == null) {
                        TenantContext.setCurrentTenant("admin");
                        TenantContext.setCurrentTenantId(null);
                        filterChain.doFilter(request, response);
                        return;
                    }

                    assert tenant != null;
                    if(tenant.equals(subdomain)){
                        TenantContext.setCurrentTenant(tenant);
                        TenantContext.setCurrentTenantId(tenantId);
                        filterChain.doFilter(request, response);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    try (PrintWriter writer = response.getWriter()) {
                        writer.write("{\"message\": \"Acceso denegado\"}");
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                try (PrintWriter writer = response.getWriter()) {
                    writer.write("{\"message\": \"Acceso denegado\"}");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private String getTenant(HttpServletRequest request) {
        var domain = request.getServerName();
        var dotIndex = domain.indexOf(".");

        String tenant = null;
        if (dotIndex != -1) {
            tenant = domain.substring(0, dotIndex);
        }

        return tenant;
    }

}
