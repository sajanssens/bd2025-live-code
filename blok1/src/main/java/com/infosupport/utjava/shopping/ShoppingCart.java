package com.infosupport.utjava.shopping;

import com.infosupport.utjava.shopping.repository.UserRepository;
import com.infosupport.utjava.shopping.service.BankingService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> orders;
    private String owner;
    private UserRepository userRepository;
    private BankingService bankingService;

    public ShoppingCart(String username, UserRepository userRepository, BankingService bankingService) {
        this.orders = new HashMap<>();
        this.owner = username;
        this.userRepository = userRepository;
        this.bankingService = bankingService;
    }

    public ShoppingCart(String username) {
        this(username, null, null);
    }

    /**
     * Add a new item to this cart.
     * When the item is already in the list, only the amount should be increased
     * and no new item added.
     *
     * @param item   the item to add
     * @param amount The amount for this item to be added.
     */
    public void add(Product item, int amount) {
        if (orders.containsKey(item)) {
            orders.put(item, orders.get(item) + amount);
        } else {
            orders.put(item, amount);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (var product : orders.keySet()) {
            total = total.add(product.price().multiply(BigDecimal.valueOf(orders.get(product))));
        }
        return total;
    }

    public Map<Product, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }

    public boolean checkout() {
        User user = this.userRepository.getUser(this.owner);
        BigDecimal balance = this.bankingService.getBalance(user.accountNumber());
        BigDecimal total = getTotal();
        if (balance.compareTo(total) >= 0) {
            this.bankingService.makePayment(user.accountNumber(), total);
            this.userRepository.addPaymentHistory(this.owner, total);
            return true;
        }
        return false;
    }
}
