package com.wojtekhejna.service;

import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;
import com.wojtekhejna.model.WordSet;

public class EncoderServiceImpl implements EncoderService {

	private Dictionary dict;

	public EncoderServiceImpl(Dictionary dict) {
		this.dict = dict;
	}

	@Override
	public String encode(Number n) {
		for (char c : n.getText()) {
			WordSet partialSet = new WordSet();
			
		}
		return null;

	}

}
