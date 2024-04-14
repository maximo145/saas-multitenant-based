package com.decode.saassharedschema.security.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A tenant owns/maintains a sub-section of the web application.
 * Can be thought of as a website within the application.
 * Users can register for multiple tenant without them
 * knowing that each separate tenant is part of one and
 * the same application.
 * So the uniqueness of user accounts is determined by the
 * combination of the user's unique ID (= email) combined
 * with the tenant ID.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String name;

}
