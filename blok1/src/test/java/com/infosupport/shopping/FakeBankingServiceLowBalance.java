package com.infosupport.shopping;

import com.infosupport.utjava.shopping.service.BankingService;

import java.math.BigDecimal;

public class FakeBankingServiceLowBalance implements BankingService {
    @Override
    public BigDecimal getBalance(String accountNumber) {
        return BigDecimal.valueOf(10L);
    }

    @Override
    public void makePayment(String accountNumber, BigDecimal payment) {

    }
}
