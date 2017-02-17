package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.SPACE;

import java.util.ArrayList;
import java.util.List;

public class PartialResult extends AbstractWord {
	private List<AbstractWord> allWords;
	
	private int remainingChars;

	public PartialResult() {
		allWords = new ArrayList<>();
	}

	public PartialResult(AbstractWord word) {
		this();
		allWords.add(word);
		remainingChars = word.getText().length;
	}

	public void appendNewWord(AbstractWord word) {
		allWords.add(word);
		
		// do this only if the word is a real word, not a number
		if (word instanceof Word) {
			remainingChars = word.getText().length;
		}
	}
	
	public char[] getText() {
		StringBuffer buf = new StringBuffer();
		for (AbstractWord word : allWords) {
			buf.append(word.getText());
		}

		return buf.toString().toCharArray();
	}

	public String getOriginalText() {
		StringBuffer buf = new StringBuffer();

		for (AbstractWord word : allWords) {
			buf.append(word.getOriginalText());
			buf.append(SPACE);
		}

		// remove last space
		if (buf.length() > 0) {
			buf.substring(0, buf.length() - 2); // remove the last space
		}

		return buf.toString();
	}

	public String toString() {
		return getOriginalText();
	}

	public int getRemainingChars() {
		return remainingChars;
	}
}
