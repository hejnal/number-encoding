package com.wojtekhejna;

import java.util.Hashtable;

import org.junit.Test;
import static org.junit.Assert.*;

import com.wojtekhejna.model.Word;

public class HashtableTest {
	@Test
	public void testHashtable() {
		Hashtable<Integer, Word> words = new Hashtable<>();
		
		// a -> 1
		// b -> 2
		// c,z -> 7
		Word w1 = new Word("abc");
		Word w2 = new Word("abz");
		
		words.put(127, w1);
		words.put(127, w2);
		
		assertEquals(2, words.size());
		
	}
}
