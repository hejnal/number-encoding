package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.DASH;
import static com.wojtekhejna.Configuration.UMLAUT;
import static com.wojtekhejna.Configuration.SPACE;

public class CompoundWord extends AbstractWord {

	public CompoundWord() {
		super();
	}

	public CompoundWord(char[] origText) {
		super(origText);
		removeSpecialChars(DASH);
		removeSpecialChars(UMLAUT);
		removeSpecialChars(SPACE);
	}

	public String toString() {
		return String.valueOf(getOrigText());
	}

}
