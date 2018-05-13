/**
 * 
 */
package com.infracast;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import com.infracast.Evaluator;

/**
 * @author Uma
 *
 */
public class EvaluatorTest {
	
	String testData1 = "";
	String testData2 = "";
	Evaluator classUnderTest = new Evaluator();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		String currentDir = new java.io.File(".").getCanonicalPath();
		testData1 = getFileContents(currentDir + "\\src\\test\\java\\com\\infracast\\testdata1");
		testData2 = getFileContents(currentDir + "\\src\\test\\java\\com\\infracast\\testdata2");
	}
	
	private String getFileContents(String fileName) throws Exception{
		// read the source file into a String
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				// add line separator while reading the source
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String contents = sb.toString();
			return contents;
		} finally {
			br.close();
		}
			
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.infracast.Evaluator#evaluate(java.lang.String)}.
	 */
	@org.junit.Test
	public void testEvaluate() {
		classUnderTest.evaluate(testData1);
	
		assertEquals(Integer.valueOf(10), classUnderTest.getBlockCount());
		assertEquals(Integer.valueOf(22), classUnderTest.getStatementCount());
		assertEquals(Integer.valueOf(16), classUnderTest.getExpressionCount());
	}
	
	/**
	 * Test method for the source with missing curly braces
	 */
	@org.junit.Test
	public void testEvaluateMissingCurlyBraces() {
		classUnderTest.evaluate(testData2);
		String expected = "Incomplete Blocks in the file as open braces count is 7  and closed braces count is 6";
		assertEquals(expected, outContent.toString().trim());
	}
	


}
