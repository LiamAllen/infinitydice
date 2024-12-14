package com.liamallen.infinitydice;

import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(name = "roll", mixinStandardHelpOptions = true, version = "0.0.0", description = "roll : rolls a die for a whole number with a given probability (i.e. 20 is equivalent to rolling a 20-sided die). Perameters(int : probability) Options(none)")
public class Roll implements Runnable{

	@Parameters(index = "0", description = "specified probability (i.e. 20 is equivalent to rolling a 20-sided die).")
	private int probability;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int probabilityToken = probability + 1;
		int randomNum = (int)(Math.random() * probabilityToken); 
		//(int)(Math.random() * (max - min) + min) to generate an integer within a specified range
		System.out.println("diceroll with probability of " + probability + " resulted in a roll of " + randomNum);
	}

}
