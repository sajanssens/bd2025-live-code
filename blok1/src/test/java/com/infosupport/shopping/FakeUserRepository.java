package com.infosupport.shopping;

import com.infosupport.utjava.shopping.User;
import com.infosupport.utjava.shopping.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FakeUserRepository implements UserRepository {
    private boolean addPaymentHistoryCalled = false;
    private String username;
    private BigDecimal payment;

    @Override
    public User getUser(String username) {
        return new User("Frank", LocalDate.of(1999, 1, 1), "123");
    }

    @Override
    public void addPaymentHistory(String username, BigDecimal payment) {
        this.addPaymentHistoryCalled = true;
        this.username = username;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public boolean isAddPaymentHistoryCalled() {
        return addPaymentHistoryCalled;
    }
}
