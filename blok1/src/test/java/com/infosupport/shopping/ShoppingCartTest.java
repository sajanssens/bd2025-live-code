package com.infosupport.shopping;

import com.infosupport.utjava.shopping.Product;
import com.infosupport.utjava.shopping.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTest {

    private final Product xbox = new Product("Xbox 360", new BigDecimal("199.99"));
    private final Product playstation = new Product("PlayStation3", new BigDecimal(250));

    @Test
    void add_oneProduct_shouldAddProductToCart() {
        // Arrange
        var sut = new ShoppingCart("Frank");

        // Act
        sut.add(xbox, 2);

        // Assert
        assertProductIsInCart(sut, xbox, 2);
    }

    @Test
    void add_twiceSameProduct_shouldAddToExistingAmount() {
        var sut = new ShoppingCart("Frank");
        sut.add(xbox, 2);
        sut.add(xbox, 3);
        assertProductIsInCart(sut, xbox, 5);
    }

    @Test
    void add_twoDifferentProducts_shouldAddBothToCart() {
        var sut = new ShoppingCart("Frank");
        sut.add(xbox, 1);
        sut.add(playstation, 2);
        assertProductIsInCart(sut, xbox, 1);
        assertProductIsInCart(sut, playstation, 2);
    }

    @Test
    void getTotal_emptyCart_shouldBeZero() {
        var sut = new ShoppingCart("Frank");
        assertEquals(BigDecimal.ZERO, sut.getTotal());
    }

    @Test
    void getTotal_twoProductsWithDifferentAmount_shouldCalculateCorrectTotal() {
        var sut = new ShoppingCart("Frank");
        sut.add(playstation, 2); // 500
        sut.add(xbox, 1); // 199.99
        assertEquals(new BigDecimal("699.99"), sut.getTotal());
    }

    @Test
    void checkout_sufficientBalance_succeeds() {
        FakeUserRepository userRepository = new FakeUserRepository();
        FakeBankingServiceHighBalance bankingService = new FakeBankingServiceHighBalance();

        var sut = new ShoppingCart("Frank", userRepository, bankingService);
        sut.add(playstation, 2); // 500
        sut.add(xbox, 1); // 199.99

        boolean checkout = sut.checkout();

        assertTrue(checkout);
        assertTrue(userRepository.isAddPaymentHistoryCalled());
        assertEquals("Frank", userRepository.getUsername());
        assertEquals(sut.getTotal(), userRepository.getPayment());
    }

    @Test
    void checkout_sufficientBalance_fails() {
        FakeUserRepository userRepository = new FakeUserRepository();
        FakeBankingServiceLowBalance bankingService = new FakeBankingServiceLowBalance();

        var sut = new ShoppingCart("Frank", userRepository, bankingService);
        sut.add(playstation, 2); // 500
        sut.add(xbox, 1); // 199.99

        boolean checkout = sut.checkout();

        assertFalse(checkout);
        assertFalse(userRepository.isAddPaymentHistoryCalled());
    }

    private void assertProductIsInCart(ShoppingCart sut, Product expectedItem, int expectedAmount) {
        assertTrue(sut.getOrders().containsKey(expectedItem));
        assertEquals(expectedAmount, sut.getOrders().get(expectedItem));
    }
}
