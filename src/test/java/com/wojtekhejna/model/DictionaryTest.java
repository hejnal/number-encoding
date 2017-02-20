package com.wojtekhejna.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.wojtekhejna.Configuration;

@RunWith(Parameterized.class)
public class DictionaryTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "dictionary.txt", 23 } });
	}

	public static Logger logger = Logger.getLogger(DictionaryTest.class);

	private String path;
	private int size;
	
	private Dictionary dict;

	public DictionaryTest(String path, int size) {
		this.path = path;
		this.size = size;
	}

	@Test
	public void testReload() {
		dict = new Dictionary(path);

		assertTrue(dict.reload());

		logger.debug("Dictionary size is " + dict.getSize());

		assertEquals(size, dict.getSize());

		logger.info("Dictionary contents is: \n" + dict);
	}
	
	@Test
	public void testGetWordSet() {
		testReload();
		List<Character> charArrays = Configuration.MAPPING.get('5');
		
		TreeSet<AbstractWord> wordSet = dict.getWordSet(charArrays, 5);
		
		logger.info("Dictionary contents is: \n" + wordSet);
	}
}
