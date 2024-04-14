package com.decode.saassharedschema.security.domain.persistence;

import com.decode.saassharedschema.security.domain.entities.Tenant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findBySlug(String slug);
    Optional<Tenant> findById(long id);
}
