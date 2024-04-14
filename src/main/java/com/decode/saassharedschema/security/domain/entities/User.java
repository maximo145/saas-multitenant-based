package com.decode.saassharedschema.security.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_user", indexes = {
        @Index(name = "users_user_name_key", columnList = "user_name", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 150)
    private String userName;

    @Column(name = "password", nullable = false)
    //@Type(type = "org.hibernate.type.TextType")
    private String password;

    @Column(name = "active")
    private Boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

}