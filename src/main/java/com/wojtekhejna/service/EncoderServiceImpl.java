package com.wojtekhejna.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

		// load the partial set for the first digit

		partialSet = dict.getWordSet(Configuration.MAPPING.get(number.getText()[currentLevel]), remainingLength);

		// if there is no item from the very first iteration
		if (partialSet.size() == 0) {
			AbstractWord nw = new CompoundWord(new char[] { number.getText()[currentLevel] });
			partialSet.add(nw);
		}

		// start checking from the second position (the first is already
		// validated)
		for (int i = 1; i < number.getLength(); ++i) {

			currentLevel = i;
			remainingLength = number.getLength() - i;

			char digit = number.getText()[i];

			Iterator<AbstractWord> it = partialSet.iterator();

			TreeSet<AbstractWord> loadedSet = null;
			List<AbstractWord> finishedWords = null;

			// iterate through the partial set
			while (it.hasNext()) {
				AbstractWord w = it.next();

				// check out if the word is not finished
				// checked after the first iteration
				if (w.getLength() == currentLevel && currentLevel < number.getLength()) {
					// load the set of new words
					if (finishedWords == null) {
						finishedWords = new ArrayList<>();
					}
					finishedWords.add(w);
					it.remove();
				} // filter the resuls that are invalid
				else {

					char c = w.getText()[currentLevel];

					// remove the word if the mapping is invalid, or if the one
					// single digit is not the correct one (it should not
					// happen)
					if (Configuration.REVERSE_MAPPING.get(c) != digit || (Character.isDigit(c) && c != digit)) {
						it.remove();
						w = null;
					}

				}
			}

			// check if we need to combine the results with the new entries
			// (do the cartesian product)

			if (finishedWords != null && finishedWords.size() > 0) {
				loadedSet = dict.getWordSet(Configuration.MAPPING.get(number.getText()[currentLevel]), remainingLength);

				Iterator<AbstractWord> it2 = finishedWords.iterator();

				while (it2.hasNext()) {
					AbstractWord w = it2.next();

					// we need to add the digit
					if (loadedSet.size() == 0 && currentLevel > 0) {

						if (!Character.isDigit(w.getText()[currentLevel - 1])) {
							StringBuffer b = new StringBuffer();
							b.append(w.getOrigText());
							b.append(Configuration.SPACE);
							b.append(digit);
							if (currentLevel < number.getLength() - 1) {
								b.append(Configuration.SPACE);
							}
							AbstractWord nw = new CompoundWord(b.toString().toCharArray());
							partialSet.add(nw);
						}
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

			// no options are to get the word encoded (after trying to add the
			// digit or loading the data from the dictionary)
			if (partialSet.size() == 0) {
				break;
			}

		}
		return formatTheOutputEncoding(number);

	}

	private String formatTheOutputEncoding(Number n) {
		Iterator<AbstractWord> it = partialSet.iterator();

		StringBuffer buf = new StringBuffer();

		if (partialSet.size() > 0) {
			int index = -1;

			while (it.hasNext()) {

				buf.append(n.getOrigText());
				buf.append(Configuration.COLON);
				buf.append(Configuration.SPACE);

				index++;

				AbstractWord w = it.next();

				buf.append(w.getOrigText());

				if (index < partialSet.size() - 1) {
					buf.append(Configuration.NEW_LINE);
				}
			}

			// if no encoding could be possible
			if (partialSet.size() == 0) {
				buf.append(n.getOrigText());
				buf.append(Configuration.COLON);
				buf.append(Configuration.SPACE);
			}
		}

		return buf.toString();
	}

}
