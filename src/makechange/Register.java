package makechange;

import java.util.Scanner;

public class Register {
	
	static Scanner input = new Scanner(System.in);
	
	static double roundingOffset = 0.0005;
	
	static double[] tenderSizes = { 20.0, 10.0, 5.0, 1.0, 0.25, 0.10, 0.05, 0.01 };
	
	static String[] tenderNames = { "Twenty dollar bill", "Ten dollar bill", "Five dollar bill", "One dollar bill",
			"Quarter", "Dime", "Nickel", "Penny" };
	

	public static void main(String[] args) {

		double cashBack = getExchangeData();
		
		if (cashBack < 0) {

			System.err.printf("You provided to little cash to cover the price!\nYou still owe: $%.2f", (-cashBack));

		} else if (cashBack == 0) {

			System.out.printf("You paid the exact amount, you are owed no change! Thank you for your business!");

		} else {
			
			makeChange(cashBack);
			
		}

		input.close();
		
	}
	
	
	public static double getExchangeData() {
		System.out.print("What is the total of your purchase?\n>>> ");
		double total = input.nextDouble();

		System.out.print("How much cash will you use to pay for it?\n>>> ");
		double cashGiven = input.nextDouble();

		double cashBack = (cashGiven - total) + roundingOffset;
		
		return cashBack;
	}
	

	public static void makeChange(double cashBack) {
		
		int i;

		boolean isFirstToPrint = true;

		double remainder;
		double tenderSize;

		int amountOfCurrentSize;

		String tenderName;
		String outPut = "";

		System.out.println("You are owed: ");
		for (i = 0; i < tenderSizes.length; i++) {

			tenderSize = tenderSizes[i];

			amountOfCurrentSize = (int) (cashBack / tenderSize);

			if (amountOfCurrentSize != 0) {
				tenderName = tenderNames[i];

				remainder = cashBack % tenderSize;
				cashBack = remainder;

				if (amountOfCurrentSize > 1.0) {
					if (tenderName.equals("Penny")) {
						tenderName = "Pennies";
					} else {
						tenderName += "s";
					}
				}

				if (!isFirstToPrint) {
					System.out.printf(", ");
				} else {
					isFirstToPrint = false;
				}

				System.out.printf("%d %s", amountOfCurrentSize, tenderName);

			}

		}

		System.out.println();
		
	}
}