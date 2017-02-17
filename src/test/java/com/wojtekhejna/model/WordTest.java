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
	public static Collection<String> data() {
		return Arrays.asList(new String[] { "testWord", "t\"est\"Word" });
	}

	public static Logger logger = Logger.getLogger(WordTest.class);
	private String text;

	public WordTest(String text) {
		this.text = text;
	}

	@Test
	public void testGetOriginalText() {
		Word word = new Word(text);
		
		logger.debug("My original text was " + word.getOriginalText());
		
		assertEquals(text, word.getOriginalText());
	}
}
