package com.techelevator;

import com.techelevator.view.VendingMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	// Declare a VendingMenu object to allow user to interact via the console.
	private VendingMenu menu;

	/* Create a constructor that initializes VendingMachineCLI
	* with an instance of VendingMenu. This is where dependency
	* interjection happens--instead of creating a new VendingMenu
	* inside VendingMachineCLI, it's passed as a parameter, making
	* it easier to manage and test. */
	public VendingMachineCLI(VendingMenu menu) {
		this.menu = menu;
	}

	/* public void run() contains the main loop that keeps
	* the program running. This method also shows the
	* main menu, gets choices, and executes choices. */
	public void run() {
		boolean running = true;
		VendingMachine myVendingMachine = new VendingMachine(); // Instantiates VendingMachine object to control logic of inventory and transactions.
		myVendingMachine.getInventory(); // Load inventory at the start of the loop.

		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // Calls getChoiceFromOptions in VendingMenu.
			// A switch statement could also be used here. Your choice.
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // User selects (1) Display[...].
				// display vending machine items
				myVendingMachine.displayItems(); // displayItems in VendingMachine is called.
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { // User selects (2) Purchase.
				boolean purchasing = true;
				// Create purchase sub-menu that operates while boolean purchasing = true;
				while(purchasing) {
					myVendingMachine.displayPurchaseMenu(); // displayPurchaseMenu in VendingMachine is called.
					String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if(choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						// Call method feedMoney(myVendingMachine);
					} else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						// Call method selectProduct(myVendingMachine);
					} else if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						// Call method finishTransaction(myVendingMachine);
						purchasing = false;  // Exit the purchasing loop.
					} else {
						System.out.println(("Invalid choice."));
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// exit
				running = false; // Exit the main menu loop.
				break;
			} else if (choice.equals(MAIN_MENU_SECRET_OPTION)) {
				// sale report
				// generateSaleReport();
			} else {
				System.out.println("Not a Choice");
				break;
			}
		}
	}


	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
