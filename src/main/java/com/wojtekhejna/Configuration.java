package com.wojtekhejna;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Configuration {
	public static char UMLAUT = '\"';
	public static char DASH = '-';
	public static char SLASH = '/';
	public static char SPACE = ' ';

	public static HashMap<Character, List<Character>> MAPPING = new HashMap<Character, List<Character>>();

	static
	{
		MAPPING.put('0', Arrays.asList('E'));
		MAPPING.put('1', Arrays.asList('J', 'N', 'Q'));
		MAPPING.put('2', Arrays.asList('R', 'W', 'X'));
		MAPPING.put('3', Arrays.asList('D', 'S', 'Y'));
		MAPPING.put('4', Arrays.asList('F', 'T'));
		MAPPING.put('5', Arrays.asList('A', 'M'));
		MAPPING.put('6', Arrays.asList('C', 'I', 'V'));
		MAPPING.put('7', Arrays.asList('B', 'K', 'U'));
		MAPPING.put('8', Arrays.asList('L', 'O', 'P'));
		MAPPING.put('9', Arrays.asList('G', 'H', 'Z'));
	}
}
