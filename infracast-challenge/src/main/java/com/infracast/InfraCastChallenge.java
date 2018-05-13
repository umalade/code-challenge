package com.infracast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main Class to invoke the evaluator
 * 
 * @author Uma
 *
 */
public class InfraCastChallenge {

	public static void main(String[] args) throws IOException {

		String currentDir = new java.io.File(".").getCanonicalPath();
		//String fileName = currentDir + "\\src\\com\\infracast\\Evaluator.java";
		String fileName = currentDir + "\\src\\main\\java\\com\\infracast\\InfracastChallenge.java";
		BufferedReader br = null;
		Evaluator evaluator = new Evaluator();
		try {
			// read the source file into a String
			br = new BufferedReader(new FileReader(fileName));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				// add line separator while reading the source
				sb.append(System.lineSeparator());
				line = br.readLine();
			}

			//expressions
			int x = 0;
			x = x+2;
			
			// parse or evaluate the source code
			evaluator.evaluate(sb.toString());

			System.out.println("No of Blocks are: " + evaluator.getBlockCount());
			System.out.println("No of Statements are: " + evaluator.getStatementCount());
			System.out.println("No of Expressions are: " + evaluator.getExpressionCount());

		} catch (FileNotFoundException fe) {
			System.out.println("Cannot find the file " + fe.getMessage());
		} finally {
			br.close();
		}

	}

}