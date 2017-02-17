package com.wojtekhejna.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;

@RunWith(Parameterized.class)
public class EncoderServiceTest {
	@Parameters
	public static Collection<Object[][]> data() {
		return Arrays.asList(new Object[][][] { { { "5624-82" }, { "mir Tor", "Mix Tor" } },
				{ { "5624-82" }, { "mir Tor", "Mix Tor" } } });
	}

	public static Logger logger = Logger.getLogger(EncoderServiceTest.class);

	private Object number;
	private Object[] words;

	public EncoderServiceTest(Object[] number, Object[] words) {
		this.number = number[0];
		this.words = words;
	}

	@Test
	public void testEncode() {
		Dictionary dict = new Dictionary("src/test/resources/dictionary.txt");
		assertTrue(dict.reload());

		EncoderService encoder = new EncoderServiceImpl(dict);

		logger.debug("The expected result is \n" + getTheFinalAnswer());

		assertEquals(getTheFinalAnswer(), encoder.encode(new Number(number.toString())));

	}

	private String getTheFinalAnswer() {
		StringBuffer buf = new StringBuffer();
		for (Object s : words) {
			buf.append(number.toString());
			buf.append(": ");
			buf.append(s.toString());
			buf.append("\n");
		}
		return buf.toString();

	}
}
