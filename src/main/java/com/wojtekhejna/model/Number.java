package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.*;

import java.util.Comparator;
import java.util.Vector;

public class Number extends AbstractWord {
	private char[] text;
	private Integer[] origDashPos;
	private Integer[] origSlashPos;

	public Number(String origText) {
		removeSpecialChars(origText);
	}

	public char[] getText() {
		return text;
	}

	public String getOriginalText() {
		StringBuffer buf = new StringBuffer(String.valueOf(text));
		for (Integer i : origDashPos) {
			buf.insert(i.intValue(), DASH);
		}
		for (Integer i : origSlashPos) {
			buf.insert(i.intValue(), SLASH);
		}
		return buf.toString();
	}

	private void removeSpecialChars(String origText) {

		int index = origText.indexOf(DASH);
		Vector<Integer> temp = new Vector<Integer>();

		while (index >= 0) {
			temp.add(index);
			index = origText.indexOf(DASH, index + 1);
		}

		origDashPos = temp.toArray(new Integer[temp.size()]);

		index = origText.indexOf(SLASH);
		temp = new Vector<Integer>();

		while (index >= 0) {
			temp.add(index);
			index = origText.indexOf(SLASH, index + 1);
		}

		origSlashPos = temp.toArray(new Integer[temp.size()]);

		text = origText.replace(String.valueOf(DASH), "").replace(String.valueOf(SLASH), "").toCharArray();

	}

}
