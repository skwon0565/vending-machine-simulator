package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {

//    private VendingMachine vendingMachine;
//
//    @Before
//    public void setup() {
//        vendingMachine = new VendingMachine();
//
//    }
//
//    /* Test purchase with insufficient funds. */
//    @Test
//    public void purchaseWithInsufficientFunds() {
//        // Assuming there's a way to simulate the selection of a product,
//        // perhaps by simulating user input or by calling a method directly.
//
//        // Arrange
//        BigDecimal price = new BigDecimal("3.00"); // Assuming the price of a product.
//        vendingMachine.feedMoney(new BigDecimal("1.00")); // User only adds $1.00
//
//        // Act & Assert
//        try {
//            vendingMachine.purchaseItem("A1"); // Let's say "A1" is a product code.
//            fail("Purchase should not be possible with insufficient funds.");
//        } catch (Exception e) {
//            // Assuming an exception is thrown when there are not enough funds.
//            // Validate the exception message if applicable.
//        }
//    }
//
//    /* Test purchase of invalid stock number. */
//    @Test(expected = IllegalArgumentException.class)
//    public void purchaseWithInvalidStockNumber() {
//        // Act
//        vendingMachine.purchaseItem("InvalidCode");
//        // Assuming purchaseItem throws IllegalArgumentException on invalid stock number.
//    }
//
//    /* Test feeding invalid cash amount. */
//    @Test(expected = IllegalArgumentException.class)
//    public void feedMoneyInvalidAmount() {
//        // Act
//        vendingMachine.feedMoney(new BigDecimal("-5"));
//        // Assuming feedMoney throws IllegalArgumentException on invalid cash amount.
//    }
//
//    /* Test whether change is returned correctly. */
//    @Test
//    public void finishTransactionReturnChange() {
//        // Arrange
//        vendingMachine.feedMoney(new BigDecimal("1.00"));
//        BigDecimal price = new BigDecimal("0.65"); // Assuming a price of a product.
//        vendingMachine.purchaseItem("A1"); // Assuming "A1" is a product code that costs $0.65.
//
//        // Act
//        BigDecimal change = vendingMachine.finishTransaction();
//
//        // Assert
//        assertEquals("Change should be the amount left after purchase.", new BigDecimal("0.35"), change);
//    }
//
//    /* Test to ensure finishTransaction is functioning as expected */
//    @Test
//    public void finishTransactionShouldResetBalance() {
//        // Arrange
//        vendingMachine.feedMoney(new BigDecimal("1.00"));
//        // Assume some purchases happened here...
//
//        // Act
//        vendingMachine.finishTransaction();
//        BigDecimal balanceAfterTransaction = vendingMachine.getBalance();
//
//        // Assert
//        assertEquals("Balance should be zero after transaction.", BigDecimal.ZERO, balanceAfterTransaction);
//    }
}