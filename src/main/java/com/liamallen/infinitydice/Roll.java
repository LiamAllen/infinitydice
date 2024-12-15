package com.liamallen.infinitydice;

import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(name = "roll", mixinStandardHelpOptions = true, version = "0.0.0", description = "roll : rolls a die for a whole number with a given probability (i.e. 20 is equivalent to rolling a 20-sided die). Perameters(int : probability) Options(none)")
public class Roll implements Runnable{

	private static boolean isOmitSuccessful; 
	
	@Option(names = {"-p", "--probability"}, description = "specified probability (i.e. 20 is equivalent to rolling a 20-sided die).")
	private static int probability; //cannot be used with --range
	
	@Option(names = {"-c", "--count"}, description = "determines the number of dice to roll as specified by the user")
	private static int count; 
	
	@Option(names = {"-r", "--range"}, description = "takes two numbers as the minimum and maximum, respectively, of the range of probably numbers, inclusive.", arity = "2")
	private static int[] range; //range.length must be equal to 2, cannot be used with --probability
	
	@Option(names = {"-o", "--omit"}, description = "whole numbers within the specified range to be omitted from generation. 99 numbers can be omitted at one time")
	private static int[] omit;
	
	@Override
	public void run() {
		// TODO fix logic for new options
		//create method logic to deal with digit omissions
		int roll;
		int[] rolls;
		
		if (checkValidOptions()) {
			if (probability != 0) { //check if probability is used instead of range
				if (count != 0 && omit != null) {//check if both roll count and omissions were specified
					rolls = rollProbability(probability, count);
					rolls = checkOmissionCompliance(omit, rolls, true);
					
					System.out.print("dicerolls with omission factored resulted in " );
					
					for (int i = 0; i < rolls.length; i++) {
						System.out.print(rolls[i] + " ");
					}
				}
				else if (omit != null) {//check if omissions were specified
					roll = rollProbability(probability);
					roll = checkOmissionCompliance(omit, roll, true);
					System.out.println("diceroll with omissions factored resulted in " + roll);
				}
				else if (count != 0) {//check if roll count was specified
					
					rolls = rollProbability(probability, count);
					
					System.out.print("dicerolls resulted in " );
					
					for (int i = 0; i < rolls.length; i++) {
						System.out.print(rolls[i] + " ");
					}
				}
				else {
					roll = rollProbability(probability);
					System.out.println("Diceroll resulted in " + roll);
				}

			}
			else if (range != null) { //check if range is used instead of probability
				if (count != 0 && omit != null) {//check if both roll count and omissions were specified
					rolls = rollRange(range[0], range[1], count);
					rolls = checkOmissionCompliance(omit, rolls, false);
					
					System.out.print("dicerolls with omission factored resulted in " );
					
					for (int i = 0; i < rolls.length; i++) {
						System.out.print(rolls[i] + " ");
					}
				}
				else if (omit != null) {//check if omissions were specified
					roll = rollRange(range[0], range[1]);
					roll = checkOmissionCompliance(omit, roll, false);
					System.out.println("diceroll with omissions factored resulted in " + roll);
				}
				else if (count != 0) {//check if roll count was specified
					
					rolls = rollRange(range[0], range[1], count);
					
					System.out.print("Dicerolls resulted in " );
					
					for (int i = 0; i < rolls.length; i++) {
						System.out.print(rolls[i] + " ");
					}
				}
				else {
					roll = rollRange(range[0], range[1]);
					System.out.println("Diceroll resulted in " + roll);
				}

			}
		}
		else {
			System.out.println("Options are not valid");
		}
		//(int)(Math.random() * (max - min) + min) to generate an integer within a specified range
	}
	
	private static boolean checkValidOptions() {
		if(probability != 0 && range != null) {
			return false; //both -p and -r cannot be used at the same time, options are not valid.
		}
		else {
			return true; //options are valid
		}
	}
	
	private static int checkOmissionCompliance(int[] omit, int diceroll, boolean isProbabilityRoll) {
		for (int i = 0; i < omit.length; i++) {
			if (diceroll == omit[i]) {
				if(isProbabilityRoll) {
					diceroll = rollProbability(probability);
					i = 0;
				}
				else {
					diceroll = rollRange(range[0], range[1]);
					i = 0;
				}
			}
			else {
				continue;
			}
		}
		
		return diceroll;
	}
	private static int[] checkOmissionCompliance(int[] omit, int[] dicerolls, boolean isProbabilityRoll) {
		for (int i = 0; i < dicerolls.length; i++) {
			for(int j = 0; j < omit.length; j++) {
				if (dicerolls[i] == omit[j]) {
					if(isProbabilityRoll) {
						dicerolls[i] = rollProbability(probability);
						j = 0;
					}
					else {
						dicerolls[i] = rollRange(range[0], range[1]);
						j = 0;
					}
				}
				else {
					continue;
				}
			}
		}
		return dicerolls;

	}
	private static int rollProbability(int probability) {
		return (int)(Math.random() * (probability + 1));
	}
	private static int[] rollProbability(int probability, int count) {
		int[] rolls = new int[count];
		for (int i = 0; i < count; i++) {
			rolls[i] = rollProbability(probability);
		}
		return rolls;
	}
	private static int rollRange(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}
	private static int[] rollRange(int min, int max, int count) {
		
		int[] rolls = new int[count];
		
		for (int i = 0; i < count; i++) {
			rolls[i] = rollRange(min, max);
		}
		
		return rolls;
	}

}
