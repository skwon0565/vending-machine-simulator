package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MachineTest {

    @BeforeEach
    public void setUp() {
    }

//    @Test
//    public void testFeedMoney() throws IOException {
//        VendingMachine vendingMachine = new VendingMachine();
//        String input = "5.00\n";
//        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//
//        vendingMachine.feedMoney();
//
//        // Ensure that the balance is correctly updated after feeding money
//        assertEquals(new BigDecimal("5.00"), vendingMachine.getBalance());
//    }

    @Test
    public void testSelectProduct() throws IOException {
        // Create a VendingMachine instance
        VendingMachine vendingMachine = new VendingMachine();

        // Load inventory into the vending machine
        vendingMachine.getInventory();

        // Set an initial balance
        vendingMachine.setBalance(new BigDecimal("10.00"));

        // Provide the input for the test using a custom Scanner
        String input = "A1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner testScanner = new Scanner(inputStream);

        // Set the custom Scanner for user input
        vendingMachine.setUserInputScanner(testScanner);

        // Run the test by calling the selectProduct method
        vendingMachine.selectProduct();

        // Ensure that the balance is correctly updated after selecting a product
        BigDecimal expectedBalance = new BigDecimal("6.95").setScale(2, RoundingMode.HALF_UP);

        // Use assertEquals for direct comparison
        assertEquals(expectedBalance, vendingMachine.getBalance());
    }




    @Test
    public void testFinishTransaction() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setBalance(new BigDecimal("1.00"));

        vendingMachine.finishTransaction();

        // Ensure that the balance is reset to zero after finishing the transaction
        assertEquals(BigDecimal.ZERO.setScale(2), vendingMachine.getBalance());
    }

    @Test
    public void testStoreTransactionLog() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setBalance(new BigDecimal("5.00"));

        File logFile = new File("Log.txt");
        if (logFile.exists()) {
            logFile.delete();
        }

        vendingMachine.storeTransactionLog("TEST ACTION", new BigDecimal("1.50"));

        // Ensure that the log file is created and contains the expected content
        assertTrue(logFile.exists());
    }
}
