package com.wojtekhejna.model;

import static com.wojtekhejna.Configuration.*;

import java.util.Comparator;
import java.util.Vector;

public class Word extends AbstractWord {
	private Integer[] origTextUmlautPos;

	public Word() {
		
	}
	
	public Word(String origText) {
		removeSpecialChars(origText);
	}

	public String getOriginalText() {
		StringBuffer buf = new StringBuffer(String.valueOf(text));
		for (Integer i : origTextUmlautPos) {
			buf.insert(i.intValue(), UMLAUT);
		}
		return buf.toString();
	}

	private void removeSpecialChars(String origText) {
		int index = origText.indexOf(UMLAUT);
		Vector<Integer> temp = new Vector<Integer>();

		while (index >= 0) {
			temp.add(index);
			index = origText.indexOf(UMLAUT, index + 1);
		}

		text = origText.replace(String.valueOf(UMLAUT), "").toCharArray();
		origTextUmlautPos = temp.toArray(new Integer[temp.size()]);
	}

	public String toString() {
		return getOriginalText();
	}

}
