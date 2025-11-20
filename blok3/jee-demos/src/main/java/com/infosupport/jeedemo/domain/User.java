package com.infosupport.jeedemo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)
@Builder @AllArgsConstructor @NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = User.FIND_BY_USERNAME_AND_PASSWORD, query = "SELECT u FROM User u WHERE u.username = :login AND u.password = :password")
})
public class User extends JPAEntity {

    public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";

    private String lastName;
    private String firstName;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 256, nullable = false)
    private String password;

    @Transient // don't store in db
    private String token;

    @ElementCollection
    private Set<String> roles;
}
