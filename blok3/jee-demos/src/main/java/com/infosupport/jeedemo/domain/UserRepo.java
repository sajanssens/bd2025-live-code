package com.infosupport.jeedemo.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import static com.infosupport.jeedemo.api.util.PasswordUtils.digest;
import static com.infosupport.jeedemo.domain.User.FIND_BY_USERNAME_AND_PASSWORD;

@ApplicationScoped
public class UserRepo extends Repo<User> {

    public User findByUsernameAndPassword(String login, String password) {
        return em.createNamedQuery(FIND_BY_USERNAME_AND_PASSWORD, User.class)
                .setParameter("login", login)
                .setParameter("password", digest(password))
                .getSingleResult();
    }

    @Override
    @Transactional
    public User create(User c) {
        c.setPassword(digest(c.getPassword()));
        return super.create(c);
    }

    @Override
    public Class<User> E() { return User.class; }
}
