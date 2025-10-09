package com.infosupport.utjava.shopping.repository;

import com.infosupport.utjava.shopping.User;

import java.math.BigDecimal;

public interface UserRepository {
    User getUser(String username);

    void addPaymentHistory(String username, BigDecimal payment);
}
