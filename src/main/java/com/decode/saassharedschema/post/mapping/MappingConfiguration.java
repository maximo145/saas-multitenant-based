package com.decode.saassharedschema.post.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("postMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PostMapping postMapping() {
        return new PostMapping();
    }
}
