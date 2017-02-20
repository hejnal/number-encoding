package com.wojtekhejna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.wojtekhejna.model.Dictionary;
import com.wojtekhejna.model.Number;
import com.wojtekhejna.service.EncoderService;
import com.wojtekhejna.service.EncoderServiceImpl;

public class NumberEncoder {

	public static void main(String... args) throws IOException {

		Dictionary dict = new Dictionary("dictionary.txt");

		dict.reload();

		EncoderService encoder = new EncoderServiceImpl(dict);

		FileReader in = null;
		BufferedReader br = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			in = new FileReader(new File(classLoader.getResource("input.txt").getFile()));
			br = new BufferedReader(in);

			String line = br.readLine();
			while (line != null) {
				String result = encoder.encode(new Number(line.toCharArray()));
				line = br.readLine();
				if (!"".equals(result)) {
					System.out.println(result);
				}
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
