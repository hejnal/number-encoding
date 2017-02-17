package com.wojtekhejna.model;

import java.util.Comparator;

public class WordComparator {
	
	public static int LEVEL_ZERO = 0;
	
	public static Comparator<AbstractWord> getComparator(final int level) {
		return new Comparator<AbstractWord>() {
			public int compare(AbstractWord word1, AbstractWord word2) {

				// check if any of the words is shorter than level
				if (word1.getText().length < level 
						&& word2.getText().length >= level) {
					return -1;
				} else if (word2.getText().length < level 
						&& word1.getText().length >= level) {
					return 1;
				} else if (word1.getText().length < level 
						&& word2.getText().length < level) {
					return 0;
				}
				
				// compare each word according to the level
				String wordText1 = String.valueOf(word1.getText()).toUpperCase().substring(level);
				String wordText2 = String.valueOf(word2.getText()).toUpperCase().substring(level);

				// ascending order
				return wordText1.compareTo(wordText2);
			}
		};
	}
}
