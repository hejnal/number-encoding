package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.DASH;
import static com.wojtekhejna.Configuration.SLASH;

public class Number extends AbstractWord {

	public Number(char[] origText) {
		super(origText);

		removeSpecialChars(DASH);
		removeSpecialChars(SLASH);
	}

}
