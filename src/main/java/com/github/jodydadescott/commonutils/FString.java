package com.github.jodydadescott.commonutils;

public class FString {

	private static final String MATCH_STRING = "{}";
	private static final String MATCH_REGEX = "\\{\\}";
	private static final String EMPTY_STRING = new String();
	private static final int MATCH_MAX = 1000;

	/**
	 * 
	 * Returns a formatted string by replacing the pattern string {} with the string
	 * representation of the provided object. For each instance of {} one object
	 * should be provided. For instance if the string contains zero occurrences of
	 * the pattern {} then zero objects should be provided, it the string contains
	 * two occurrences of the patter {} then two objects should be provided. If
	 * there is a mismatch between the pattern {} and the number of objects the
	 * returned message will reflect this in the message text.
	 * 
	 * @param message   This is the message with zero or more occurrences of the
	 *                  pattern {}
	 * @param object(s) Zero or more objects
	 * @return String The formatted message
	 */
	public static String format(String message, Object... objects) {

		assert message != null;
		assert objects != null;

		int matchCount = getMatchCount(message);

		String warning = null;

		if (matchCount != objects.length) {
			warning = String.format("Warning: the expected number of objects is %s but the actual number is %s",
					objects.length, matchCount);
		}

		for (Object o : objects) {
			String objString = String.valueOf(o);
			message = message.replaceFirst(MATCH_REGEX, objString);
		}

		if (warning != null) {
			return warning + " :: " + message;
		}

		return message;
	}

	/**
	 * 
	 * Test if an object is with a given name is null and throws an assertion error
	 * if true. The name should never be null.
	 * 
	 * @param name   The name of the object to be tested
	 * @param object The object to be tested
	 * @throws java.lang.AssertionError
	 * 
	 */
	public static void assertNotNull(String name, Object object) {
		if (object == null) {
			throw new AssertionError("Setting " + name + " is null");
		}
	}

	/**
	 * 
	 * Test if an object is with a given name is null or is empty (if the object is
	 * of type String) and throws an assertion error if true. The name should never
	 * be null.
	 * 
	 * @param name   The name of the object to be tested
	 * @param object The object to be tested
	 * @throws java.lang.AssertionError
	 * 
	 */
	public static void assertNotEmpty(String name, Object o) {
		if (o == null) {
			throw new AssertionError("Setting " + name + " is null");
		}
		if (o.equals("")) {
			throw new AssertionError("Setting " + name + " is empty");
		}
	}

	/**
	 * Counts the occurrence of the pattern {} in a provided String
	 * 
	 * @param input A String with zero or more occurrences of the pattern {}
	 * @return The number of occurrences of the provided pattern
	 * 
	 */
	private static int getMatchCount(String input) {

		int counter = 0;
		int sanity = 0;
		while (input.contains(MATCH_STRING)) {
			if (sanity++ > 1000) {
				return 1000;
			}
			counter++;
			input = input.replaceFirst(MATCH_REGEX, EMPTY_STRING);
		}

		if (counter < 0) {
			return 0;
		}
		if (counter > MATCH_MAX) {
			return 0;
		}

		return counter;
	}

}
