package com.wojtekhejna.model;

public abstract class AbstractWord {

	public AbstractWord() {

	}

	public AbstractWord(char[] origText) {
		this.origText = origText;
		this.text = String.valueOf(origText).toUpperCase().toCharArray();
	}

	protected char[] origText;
	protected char[] text;

	public char[] getText() {
		return text;
	}

	public char[] getOrigText() {
		return origText;
	}

	final protected void removeSpecialChars(char c) {
		StringBuffer buf = new StringBuffer(String.valueOf(text));
		int index = -1;
		while ((index = buf.indexOf(String.valueOf(c))) != -1) {
			buf.deleteCharAt(index);
		}
		text = buf.toString().toCharArray();
	}

	final public int getLength() {
		return text.length;
	}
	
	public String toString() {
		return String.valueOf(getOrigText());
	}
}
