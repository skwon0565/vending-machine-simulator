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

	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingMachine myVendingMachine = new VendingMachine();
			myVendingMachine.getInventory();

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println("\n" + "Current Money Provided: " + myVendingMachine.getBalance());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				myVendingMachine.displayPurchaseMenu();
				String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if(choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					// Take to feed money screen.
				} else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					// Take to menu screen so the user can select the product.
				} else if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					// Take to main menu.
				} else {
					System.out.println(("Not a Choice"));
				}

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// exit
				running = false;
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
