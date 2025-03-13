package controller;

import utility.ScannerUtil;

public class InputHandler implements ScannerUtil{	
	public static void pressEnterToContinue() {
		System.out.println(" Press Enter to Continue...");
		scan.nextLine();
		clearScreen();
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public static int getValidIntInput(String prompt, int min, int max) {		
		while(true) {
			System.out.print(prompt);
			
			try {
				String input = scan.nextLine();
				int number = Integer.parseInt(input);
				
				if(number >= min && number <= max) return number;
				else System.out.printf(" Input must be between %d - %d!\n", min, max);
			} catch (Exception e){
				System.out.println(" Input must be Integer!");
			}
		}
	}
	
	public static String getValidStringInput(String prompt) {
		while(true) {
			System.out.print(prompt);
			
			String input = scan.nextLine();
			
			if(input == null) {
				System.out.println(" Input cannot be empty!");
			}
			else {
				return input;
			}
		}
	}
}
