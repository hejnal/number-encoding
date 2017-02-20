package com.wojtekhejna.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.wojtekhejna.Configuration;
import com.wojtekhejna.exceptions.InvalidDigitException;

public class Dictionary {
	private Map<Integer, TreeSet<AbstractWord>> wordMap;
	private String path;
	private int size;

	public Dictionary() {
	}

	public Dictionary(String path) {
		this.path = path;
	}

	public Dictionary(Map<Integer, TreeSet<AbstractWord>> wordMap) {
		this.wordMap = wordMap;
	}

	public boolean reload() {

		wordMap = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String line;
			while ((line = br.readLine()) != null) {
				AbstractWord word = new Word(line.toCharArray());
				int length = word.getText().length;

				if (wordMap.containsKey(length)) {
					wordMap.get(length).add(word);
				} else {
					TreeSet<AbstractWord> words = new TreeSet<AbstractWord>(
							WordComparator.getComparator(WordComparator.LEVEL_ZERO));
					words.add(word);

					wordMap.put(length, words);
				}
				this.size++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getSize() {
		return size;
	}

	public Map<Integer, TreeSet<AbstractWord>> getWordMap() {
		return wordMap;
	}

	public TreeSet<AbstractWord> getWordSet(List<Character> firstChars, int maxLength) {
		TreeSet<AbstractWord> wordSet = new TreeSet<AbstractWord>(
				WordComparator.getComparator(WordComparator.LEVEL_ZERO));

		// get only those words that are not too long
		for (int i = 1; i <= maxLength; ++i) {
			TreeSet<AbstractWord> tempSet = wordMap.get(i);

			if (tempSet != null) {
				// filter the tempSet according to the requested letters
				for (Character c : firstChars) {
					wordSet.addAll(tempSet.subSet(new Word(new char[] { c }), true,
							new Word(new char[] { (char) (c + 1) }), false));
				}
			}
		}

		return wordSet;
	}

	public void addWord(AbstractWord w) {
		int length = w.getLength();

		if (wordMap.containsKey(length)) {
			wordMap.get(length).add(w);
		} else {
			TreeSet<AbstractWord> words = new TreeSet<AbstractWord>(
					WordComparator.getComparator(WordComparator.LEVEL_ZERO));
			words.add(w);

			wordMap.put(length, words);
		}
		this.size++;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		Iterator<TreeSet<AbstractWord>> it = wordMap.values().iterator();

		while (it.hasNext()) {
			Iterator<AbstractWord> it2 = it.next().iterator();
			while (it2.hasNext()) {
				buf.append(it2.next().getOrigText());
				buf.append("\n");
			}
		}

		return buf.toString();
	}

}
