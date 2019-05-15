package us.jodyscott.commonutils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FStringTest {

	private static final Logger LOG = LoggerFactory.getLogger(FStringTest.class);

	@Test
	public void test() {

		assert "This is a replace1 test".equals(FString.format("This is a {} test", "replace1"));

		assert "This is a replace1 replace2 test"
				.equals(FString.format("This is a {} {} test", "replace1", "replace2"));

		LOG.info("Test completed successfully");

	}

}
