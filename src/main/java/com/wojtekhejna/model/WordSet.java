package com.wojtekhejna.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.wojtekhejna.Configuration;
import com.wojtekhejna.exceptions.InvalidDigitException;

public class WordSet<T extends AbstractWord> extends TreeSet<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1453544354L;

	public WordSet() {
		super();
	}

	public WordSet(Comparator<AbstractWord> comparator) {
		super(comparator);
	}

	public WordSet(Collection<T> collection) {
		super(collection);
	}

	public WordSet(SortedSet<T> sortedSet) {
		super(sortedSet);
	}
	
	public WordSet<T> getNextLevel(char digit, int currentLevel) throws InvalidDigitException {
		if (!Character.isDigit(digit)) {
			throw new InvalidDigitException("A digit is invalid");
		}
		
		WordSet<T> nextLevelWords = new WordSet<T>(WordComparator.getComparator(++currentLevel));

		for (Character c : Configuration.MAPPING.get(digit)) {
			// adds just the words starting with the mapped character
			
			nextLevelWords.addAll(this.subSet(new Word(c.toString()), true,
					new T(String.valueOf((char) (c + 1))), false));
		}
		return nextLevelWords;
	}
}
