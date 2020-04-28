package com.github.jodydadescott.commonutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FString {

	private static final Logger LOG = LoggerFactory.getLogger(FString.class);

	private static final String MATCH_STRING = "{}";
	private static final String MATCH_REGEX = "\\{\\}";
	private static final String EMPTY_STRING = new String();

	public static String format(String message, Object... objects) {

		assert message != null;
		assert objects != null;

		int matchCount = getMatchCount(message);

		if (matchCount != objects.length) {
			LOG.warn(String.format("The expected number of objects is %s but the actual number is %s", objects.length,
					matchCount));
		}

		for (Object o : objects) {
			String objString = String.valueOf(o);
			message = message.replaceFirst(MATCH_REGEX, objString);
		}

		return message;
	}

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
		return counter;
	}

}
