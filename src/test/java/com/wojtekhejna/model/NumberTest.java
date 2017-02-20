package com.wojtekhejna.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.apache.log4j.Logger;

@RunWith(Parameterized.class)
public class NumberTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "1234-5", "12345" }, { "1234-/5", "12345" } });
	}

	public static Logger logger = Logger.getLogger(NumberTest.class);
	private String inputText;
	private String expectedText;

	public NumberTest(String inputText, String expectedText) {
		this.inputText = inputText;
		this.expectedText = expectedText;
	}

	@Test
	public void testGetOriginalText() {

		Number number = new Number(inputText.toCharArray());

		logger.debug("My original text was " + String.valueOf(number.getOrigText()));

		assertEquals(expectedText, String.valueOf(number.getText()));
	}

	@Test
	public void testGetLengthText() {

		Number number = new Number(inputText.toCharArray());
		assertEquals(expectedText.length(), number.getLength());
	}
}
