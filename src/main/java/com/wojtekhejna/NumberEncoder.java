package com.wojtekhejna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;
import com.wojtekhejna.service.EncoderService;
import com.wojtekhejna.service.EncoderServiceImpl;

public class NumberEncoder {

	public static Logger logger = Logger.getLogger(NumberEncoder.class);

	public static void main(String... args) throws IOException {

		Dictionary dict = new Dictionary("src/main/resources/dictionary.txt");

		dict.reload();
		
		EncoderService encoder = new EncoderServiceImpl(dict);

		FileReader in = null;
		BufferedReader br = null;
		try {
			in = new FileReader("src/main/resources/input.txt");
			br = new BufferedReader(in);
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				String result = encoder.encode(new Number(line.toCharArray()));
				line = br.readLine();
				logger.debug("The returned result is \n" + result);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (br != null) {
				br.close();
			}
		}
	}
}
