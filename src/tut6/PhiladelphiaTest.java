package tut6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PhiladelphiaTest {

	@Test
	void testIsItSunny() {
		//fail("Not yet implemented");
		assertTrue(Philadelphia.isItSunny());
	}
	
	@Test
	void testIsItSunny2() {
		//fail("Not yet implemented");
		assertFalse(Philadelphia.isItSunny());
	}

	@Test
	void testIsItSunny3() {
		//fail("Not yet implemented");
		assertEquals(true, Philadelphia.isItSunny());
	}
	
	@Test
	void testIsItSunny4() {
		//fail("Not yet implemented");
		assertTrue(null);
	}
}
