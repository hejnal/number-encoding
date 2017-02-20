package com.wojtekhejna;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Configuration {
	public static char UMLAUT = '\"';
	public static char DASH = '-';
	public static char SLASH = '/';
	public static char SPACE = ' ';
	public static char COLON = ':';
	public static String NEW_LINE = System.getProperty("line.separator");

	public static HashMap<Character, List<Character>> MAPPING = new HashMap<Character, List<Character>>();

	public static HashMap<Character, Character> REVERSE_MAPPING = new HashMap<Character, Character>();

	static {
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

		REVERSE_MAPPING.put('A', '5');
		REVERSE_MAPPING.put('B', '7');
		REVERSE_MAPPING.put('C', '6');
		REVERSE_MAPPING.put('D', '3');
		REVERSE_MAPPING.put('E', '0');
		REVERSE_MAPPING.put('F', '4');
		REVERSE_MAPPING.put('G', '9');
		REVERSE_MAPPING.put('H', '9');
		REVERSE_MAPPING.put('I', '6');
		REVERSE_MAPPING.put('J', '1');
		REVERSE_MAPPING.put('K', '7');
		REVERSE_MAPPING.put('L', '8');
		REVERSE_MAPPING.put('M', '5');
		REVERSE_MAPPING.put('N', '1');
		REVERSE_MAPPING.put('O', '8');
		REVERSE_MAPPING.put('P', '8');
		REVERSE_MAPPING.put('Q', '1');
		REVERSE_MAPPING.put('R', '2');
		REVERSE_MAPPING.put('S', '3');
		REVERSE_MAPPING.put('T', '4');
		REVERSE_MAPPING.put('U', '7');
		REVERSE_MAPPING.put('V', '6');
		REVERSE_MAPPING.put('W', '2');
		REVERSE_MAPPING.put('X', '2');
		REVERSE_MAPPING.put('Y', '3');
		REVERSE_MAPPING.put('Z', '9');
	}
}
