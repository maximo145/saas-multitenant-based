package com.decode.saassharedschema.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapping userMapping() {
        return new UserMapping();
    }
    @Bean
    public TenantMapping tenantMapping() {
        return new TenantMapping();
    }

}
