package makechange;

import java.util.Scanner;

public class Register {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Double[] tenderSizes = { 20.0, 10.0, 5.0, 1.0, 0.25, 0.10, 0.05, 0.01 };
		String[] tenderNames = { "Twenty dollar bill", "Ten dollar bill", "Five dollar bill", "One dollar bill",
				"Quarter", "Dime", "Nickel", "Penny", "Pennies" };

		System.out.print("What is the total of your purchase?\n>>> ");
		double total = input.nextDouble();

		System.out.print("How much cash will you use to pay for it?\n>>> ");
		double cashGiven = input.nextDouble();

		double cashToReturn = (double) Math.round((cashGiven - total) * 100) / 100 + 0.005;
		System.out.println(cashToReturn);

		if (cashToReturn < 0) {

			System.err.printf("You provided to little cash to cover the price!\nYou still owe: $%.2f", (-cashToReturn));

		} else if (cashToReturn == 0) {

			System.out.printf("You paid the exact amount, you are owed no change! Thank you for your business!");

		} else {
			int amountOfCurrentSize;
			double remainder;
			double billSize;

			boolean isMultiple = false;
			boolean isFirstToPrint = true;
			boolean isPenny = false;

			String addOn;

			System.out.println("You are owed: ");
			for (int i = 0; i < tenderSizes.length; i++) {
				addOn = "";

				billSize = tenderSizes[i];

				amountOfCurrentSize = (int) (cashToReturn / billSize);

				remainder = cashToReturn % billSize;
				cashToReturn = remainder;

				isMultiple = (amountOfCurrentSize > 1.0);
				isPenny = (i == tenderSizes.length - 1);

				if (isMultiple) {
					if (isPenny) {
						i++;
					} else {
						addOn = "s";
					}
				}

				if (amountOfCurrentSize != 0) {

					if (!isFirstToPrint) {
						System.out.printf(", ");
					} else {
						isFirstToPrint = false;
					}

					System.out.printf("%d %s", amountOfCurrentSize, tenderNames[i] + addOn);

				}

			}

			System.out.println();

		}

		input.close();
	}
}