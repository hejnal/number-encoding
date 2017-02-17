package com.wojtekhejna.model;

public abstract class AbstractWord {
	protected char[] text;

	public char[] getText() {
		return text;
	}

	public abstract String getOriginalText();
}
