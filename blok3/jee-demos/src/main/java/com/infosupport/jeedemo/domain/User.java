package com.infosupport.jeedemo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

import static com.infosupport.jeedemo.api.util.PasswordUtils.digest;

@Entity @NoArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)
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

    @Column(length = 100, nullable = false)
    private String password;

    @Transient // don't store in db
    private String token;

    @ElementCollection
    private Set<String> roles;

    private User(String username, String hashedPassword) {
        this.username = username;
        this.password = hashedPassword;
    }

    public static User hashed(String username, String rawPassword) {
        return new User(username, digest(rawPassword));
    }
}
