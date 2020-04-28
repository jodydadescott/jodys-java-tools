package com.github.jodydadescott.commonutils;

import org.junit.Test;

import com.github.jodydadescott.commonutils.FString;

public class FStringTest {

	@Test
	public void test1() {

		assert "This is a replace1 test".equals(FString.format("This is a {} test", "replace1"));

		assert "This is a replace1 replace2 test"
				.equals(FString.format("This is a {} {} test", "replace1", "replace2"));

		assert "Warning: the expected number of objects is 2 but the actual number is 3 :: This is a replace1 replace2 {} test"
				.equals(FString.format("This is a {} {} {} test", "replace1", "replace2"));

	}

	@Test
	public void test2() {

		// This should not result in an AssertionError
		FString.assertNotNull("testobject", "not null not empty");

		// This should result in an AssertionError
		try {
			FString.assertNotNull("testobject", null);
			assert false : "Assertion should have been thrown";

		} catch (AssertionError e) {

		}

		// This should not result in an AssertionError
		FString.assertNotEmpty("testobject", "not null not empty");

		// This should result in an AssertionError
		try {
			FString.assertNotEmpty("testobject", null);
			assert false : "Assertion should have been thrown";

		} catch (AssertionError e) {

		}

		// This should result in an AssertionError
		try {
			FString.assertNotEmpty("testobject", "");
			assert false : "Assertion should have been thrown";

		} catch (AssertionError e) {

		}

	}

}
