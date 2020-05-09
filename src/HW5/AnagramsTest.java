package HW5;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnagramsTest {
	@Test
	public void test() {
		String[] args = null;
		Anagrams.main(args);
		assertEquals(Anagrams.maxEntriesTest, 
				"[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}

}