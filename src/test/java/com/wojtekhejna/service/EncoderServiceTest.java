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

import com.wojtekhejna.Configuration;
import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;

@RunWith(Parameterized.class)
public class EncoderServiceTest {
	@Parameters
	public static Collection<Object[][]> data() {
		return Arrays.asList(new Object[][][] { { { "5624-82" }, { "mir Tor", "Mix Tor" } },
				{ { "5624-82" }, { "mir Tor", "Mix Tor" } }, { { "4824" }, { "fort", "Tor 4", "Torf" } },
				{ { "04824" }, { "0 fort", "0 Tor 4", "0 Torf" } },
				{ { "10/783--5" }, { "je Bo\" da", "je bo\"s 5", "neu o\"d 5" } },
				{ { "-8\\/12188169241-76" }, { "" } } });
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
		Dictionary dict = new Dictionary("dictionary.txt");

		assertTrue(dict.reload());

		EncoderService encoder = new EncoderServiceImpl(dict);

		String result = encoder.encode(new Number(number.toString().toCharArray()));

		logger.debug("The expected result is \n" + getTheFinalAnswer());
		logger.debug("The returned result is \n" + result);

		assertEquals(getTheFinalAnswer(), result);

	}

	private String getTheFinalAnswer() {
		StringBuffer buf = new StringBuffer();

		int index = -1;

		for (Object s : words) {

			++index;

			if (!"".equals(s.toString())) {
				buf.append(number.toString());
				buf.append(": ");
				buf.append(s.toString());
				if (index < words.length - 1) {
					buf.append(Configuration.NEW_LINE);
				}
			}
		}
		return buf.toString();

	}
}
