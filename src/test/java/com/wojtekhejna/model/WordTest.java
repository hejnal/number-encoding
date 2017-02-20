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
public class WordTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "t\"est\"Word", "TESTWORD" } });
	}

	public static Logger logger = Logger.getLogger(WordTest.class);
	private String inputText;
	private String expectedText;

	public WordTest(String inputText, String expectedText) {
		this.inputText = inputText;
		this.expectedText = expectedText;
	}

	@Test
	public void testGetOriginalText() {

		Word word = new Word(inputText.toCharArray());

		logger.debug("My original text was " + String.valueOf(word.getOrigText()));

		assertEquals(expectedText, String.valueOf(word.getText()));
	}

	@Test
	public void testGetLengthText() {

		Word word = new Word(inputText.toCharArray());
		assertEquals(expectedText.length(), word.getLength());
	}
}
