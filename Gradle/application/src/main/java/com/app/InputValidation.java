package com.app;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class InputValidation{

	Scanner scan = new Scanner(System.in);

	public String stringInput(){
		String textToReturn;
		textToReturn = scan.nextLine();

		if (textToReturn.isEmpty()) {
			textToReturn = null;
		}

		return textToReturn;
	}


	


	public int getIntegerInput(int min, int max){
		boolean valid = true;
        int input = 0;

        do {
			try {
				input = Integer.valueOf(scan.nextLine());
	            valid = true;
			} catch (NumberFormatException e){
				System.out.println("Invalid Input");
				valid = false;
			}

            if(input <= min || input > max) {
                valid = false;
                System.out.println(String.format("Only accepts values %s - %s", String.valueOf(min), String.valueOf(max)));
            }

        } while (!valid);

        return input;
	}

}
