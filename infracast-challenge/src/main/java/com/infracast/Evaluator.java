/**
 * 
 */
package com.infracast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Evaluates a string and prints no.of block, expressions & statements in a given string.
 * 
 * @author Uma
 *
 */
public class Evaluator {
	
	private Integer blockCount = 0;
	private Integer expressionCount = 0;
	private Integer statementCount = 0;
	
	public void evaluate(String classContent) {

		// number of blocks, statements and expressions
		// A block in Java is a group of one or more statements enclosed in braces.
		// statement ends with ;
		// expression all java operators usage are expression
		String[] arrayOpenBraces = classContent.split("\\{");
		String[] arrayClosedBraces = classContent.split("}");
		if (arrayOpenBraces.length == arrayClosedBraces.length) {
			blockCount = arrayOpenBraces.length;
		} else {
			System.out.println(String.format(
					"Incomplete Blocks in the file as open braces count is %d  and closed braces count is %d",
					arrayOpenBraces.length, arrayClosedBraces.length));
		}

		// statement ends with ;
		String[] arraySemiColons = classContent.split(";");
		
		//ignore import statements
		List<String> statements = Stream.of(arraySemiColons).filter(string -> !string.contains("import")).collect(Collectors.toList());
		
		//ignore package statement
		statements = statements.stream().filter(string -> !string.contains("package")).collect(Collectors.toList());
				
		statementCount = statements.size();
	
		//expressions
		int x = 0;
		x = x+2;

		//  regex for expressions
		//String regex = "^([-+/*]\\d+(\\.\\d+)?)*";
		String regex = ".*=[^=]*";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(classContent);
		while (m.find()) {
			expressionCount++;
		}
	}

	public Integer getBlockCount() {
		return blockCount;
	}

	public Integer getExpressionCount() {
		return expressionCount;
	}

	public Integer getStatementCount() {
		return statementCount;
	}

}
