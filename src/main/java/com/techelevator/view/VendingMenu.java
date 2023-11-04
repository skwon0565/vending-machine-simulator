package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class VendingMenu {

	private PrintWriter out;
	private Scanner in;

	public VendingMenu(InputStream input, OutputStream output) { // Handles user input/output from console.
		this.out = new PrintWriter(output); // Uses a PrintWriter to write to System.out in VendingMachineCLI.
		this.in = new Scanner(input); // Uses a Scanner to read from System.in in VendingMachineCLI.
	}

	public Object getChoiceFromOptions(Object[] options) { // Reads user input and tries to parse it as an int if it's an option.
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) { // Iterates through provided menu options and displays them to user.
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
