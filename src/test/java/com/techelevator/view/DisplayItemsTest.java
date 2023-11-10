package com.techelevator.view;

import com.techelevator.VendingItems;
import com.techelevator.VendingMachine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DisplayItemsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        System.out.println("This is the start of the Test");
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void breakDown() {
        System.out.println("Test End");
        System.setOut(originalOut);
    }
    @Test
    public void check_inventory_load_return_header_line() throws FileNotFoundException {
        VendingMachine vendingMachine = new VendingMachine();
        String expectedOutput = String.format("%-3s %-20s %-5s %10s%n", "Slot" + "  ||", "Name" + "             ||","Price" + "  ||", "Inventory");
        expectedOutput += String.format("%-8s %-20s %-10.2f %-10s\n", "A1", "Potato Crisps", 3.05, "Chip");
        vendingMachine.displayItems();
        assertEquals(expectedOutput, outContent.toString());
    }
}
