/**
 * 
 */
package com.liamallen.infinitydice;

/**
 * 
 */

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "echo", mixinStandardHelpOptions = true, version = "0.0.0", description = "echo : echoes any given string. Perameters(string : echoText) Options(none)")
public class Echo implements Runnable{

	/**
	 * @param args
	 */
	
	@Parameters(index = "0", description = "echo")
	private String echo;
	
	@Override
	public void run() {
		System.out.println(echo);
	}

}
