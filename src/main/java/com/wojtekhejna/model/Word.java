package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.DASH;
import static com.wojtekhejna.Configuration.UMLAUT;

public class Word extends AbstractWord {

	public Word() {
		super();
	}

	public Word(char[] origText) {
		super(origText);
		removeSpecialChars(DASH);
		removeSpecialChars(UMLAUT);
	}
}
