package com.wojtekhejna.service;

import java.util.Iterator;
import java.util.TreeSet;

import com.wojtekhejna.Configuration;
import com.wojtekhejna.model.AbstractWord;
import com.wojtekhejna.model.CompoundWord;
import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;
import com.wojtekhejna.model.WordComparator;

public class EncoderServiceImpl implements EncoderService {

	private Dictionary dict;

	private TreeSet<AbstractWord> partialSet = new TreeSet<AbstractWord>(
			WordComparator.getComparator(WordComparator.LEVEL_ZERO));

	public EncoderServiceImpl() {
	}

	public EncoderServiceImpl(Dictionary dict) {
		this();
		this.dict = dict;
	}

	@Override
	public String encode(Number number) {

		int remainingLength = number.getText().length;
		int currentLevel = 0;

		partialSet = dict.getWordSet(Configuration.MAPPING.get(number.getText()[currentLevel]), remainingLength);

		for (char digit : number.getText()) {

			Iterator<AbstractWord> it = partialSet.iterator();

			TreeSet<AbstractWord> loadedSet = null;
			TreeSet<AbstractWord> finishedWords = null;

			while (it.hasNext()) {
				AbstractWord w = it.next();

				// check out if the word is not finished
				// checked after the first iteration
				if (currentLevel <= number.getLength() - 1 && w != null && w.getLength() == currentLevel) {
					// load the set of new words
					if (finishedWords == null) {
						finishedWords = new TreeSet<>(WordComparator.getComparator(WordComparator.LEVEL_ZERO));
					}
					finishedWords.add(w);
					it.remove();
				} else {
					char c = w.getText()[currentLevel];

					if (Character.isDigit(c) && c != digit || Configuration.REVERSE_MAPPING.get(c) != digit) {
						it.remove();
						w = null;
					}

				}
			}
			
			// if there is no item from the very first iteration
			if (partialSet.size() == 0 && finishedWords == null) {
				AbstractWord nw = new CompoundWord(new char[] { digit });
				partialSet.add(nw);
			}

			// check if we need to combine the results with the new entries
			// (do the cartesian product)

			if (finishedWords != null && finishedWords.size() > 0) {
				loadedSet = dict.getWordSet(Configuration.MAPPING.get(number.getText()[currentLevel]), remainingLength);

				Iterator<AbstractWord> it2 = finishedWords.iterator();

				while (it2.hasNext()) {
					AbstractWord w = it2.next();

					// we need to add the digit
					if (loadedSet.size() == 0 && currentLevel > 0
							&& !Character.isDigit(w.getText()[currentLevel - 1])) {
						StringBuffer b = new StringBuffer();
						b.append(w.getOrigText());
						b.append(Configuration.SPACE);
						b.append(digit);
						if (currentLevel < number.getLength() - 1) {
							b.append(Configuration.SPACE);
						}
						AbstractWord nw = new CompoundWord(b.toString().toCharArray());
						partialSet.add(nw);
					} else {
						for (AbstractWord w2 : loadedSet) {
							StringBuffer b = new StringBuffer();
							b.append(w.getOrigText());
							b.append(Configuration.SPACE);
							b.append(w2);
							AbstractWord nw = new CompoundWord(b.toString().toCharArray());
							partialSet.add(nw);
						}
					}
				}
			}

			++currentLevel;
			--remainingLength;
		}
		return formatTheOutputEncoding(number);

	}

	private String formatTheOutputEncoding(Number n) {
		Iterator<AbstractWord> it = partialSet.iterator();

		StringBuffer buf = new StringBuffer();

		int index = -1;

		while (it.hasNext()) {

			index++;

			AbstractWord w = it.next();
			buf.append(n.getOrigText());
			buf.append(Configuration.COLON);
			buf.append(Configuration.SPACE);
			buf.append(w.getOrigText());

			if (index < partialSet.size() - 1) {
				buf.append(Configuration.NEW_LINE);
			}
		}

		return buf.toString();
	}

}
