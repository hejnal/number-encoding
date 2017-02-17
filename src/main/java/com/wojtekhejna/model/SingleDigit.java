package com.wojtekhejna.model;

public class SingleDigit extends AbstractWord {

	public SingleDigit() {
	}

	public SingleDigit(int digit) {
		text = new char[] { String.valueOf(digit).charAt(0) };
	}

	@Override
	public String getOriginalText() {
		return String.valueOf(text);
	}

}
