/**
 * 
 */
package com.liamallen.infinitydice;

import java.util.Scanner;

import picocli.CommandLine;
import picocli.CommandLine.*;

/**
 * 
 */
@Command(name = "infinitydice", mixinStandardHelpOptions = true, version = "0.0.0", description = "Java CLI for generating outcomes for a variety of probabilities", subcommands = {
		Echo.class})
public class App implements Runnable{

	public static int appStatus; //value of 1 = program exited, value 0 = program is running
	public static Scanner inputScanner; //java.util.Scanner() object for gathering input from the console
	public static String input; //String input gathered from inputScanner objRefVar
	public static String arguments; //String variable to contain arguments for passing to command classes
	/**
	 * @param args
	 */
	
	@Override
	public void run() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				int exitCode = new CommandLine(new App()).execute(args);
				System.exit(exitCode);
			}
	}
