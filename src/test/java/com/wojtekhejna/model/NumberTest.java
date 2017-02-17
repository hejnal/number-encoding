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
	public static Collection<String> data() {
		return Arrays.asList(new String[] { "12345", "1234-5", "1234-/5" });
	}

	public static Logger logger = Logger.getLogger(NumberTest.class);
	private String text;

	public NumberTest(String text) {
		this.text = text;
	}

	@Test
	public void testGetOriginalText() {
		Number number = new Number(text);

		logger.debug("My original text was " + number.getOriginalText());

		assertEquals(text, number.getOriginalText());
	}
}
