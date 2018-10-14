import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	@Test
	void testToString() {
		
		// Zero as an edge case
		assertEquals(new BigNumber("0").toString(), "0");
		
		// The program can handle multiple leading zeroes while retaining the value '0'
		assertEquals(new BigNumber("0000").toString(), "0");
		assertEquals(new BigNumber("008").toString(), "8");
		assertEquals(new BigNumber("-008").toString(), "-8");
		
		// Negative numbers are printed correctly
		assertEquals(new BigNumber("-795727816000888777").toString(), "-795727816000888777");
		assertEquals(new BigNumber("-99999").toString(), "-99999");
	}

	// TODO: Figure out what's wrong with this
	@Test
	void testInvalidFormatException() {
		// Inputs that have a non-numerical value somewhere in the middle are not acceptable
		Throwable exception = assertThrows(InvalidFormatException.class, () -> {
			BigNumber x = new BigNumber("22-99999"); 
		});
		
		assertEquals("22-99999", exception.getMessage());
		
		exception = assertThrows(InvalidFormatException.class, () -> {
			BigNumber y = new BigNumber("+9"); 
		});
		
		assertEquals("+9", exception.getMessage());
		
	}
	
	@Test
	void testAdd() {
		
		// Adding a number and its negated value should equals 0
		assertEquals(new BigNumber("900").add(new BigNumber("-900")).toString(), "0");
		assertEquals(new BigNumber("-97483574971618").add(new BigNumber("97483574971618")).toString(), "0");
		
		// Adding two positive numbers results in a positive value
		assertEquals(new BigNumber("500").add(new BigNumber("400")).toString(), "900");
		
		// Adding two positive numbers with different number of digits results in the correct value
		assertEquals(new BigNumber("45812").add(new BigNumber("88")).toString(), "45900");
		assertEquals(new BigNumber("997259").add(new BigNumber("6271")).toString(), "1003530");
		
		//Adding two negative numbers with different number of digits results in the correct value
		assertEquals(new BigNumber("-45812").add(new BigNumber("-88")).toString(), "-45900");
		assertEquals(new BigNumber("-997259").add(new BigNumber("-6271")).toString(), "-1003530");
		
		// Adding two numbers with different values results in the correct value
		assertEquals(new BigNumber("-500").add(new BigNumber("400")).toString(), "-100");
		assertEquals(new BigNumber("6000").add(new BigNumber("-100")).toString(), "5900");
		assertEquals(new BigNumber("19450").add(new BigNumber("-100")).toString(), "19350");
	}

	@Test
	void testSubtract() {
		
		// Subtracting two negative numbers results in a positive value
		assertEquals(new BigNumber("-1000").subtract(new BigNumber("-15000")).toString(), "14000");
		
		// Subtracting two negative numbers results in a negative number if the first is less than the second
		assertEquals(new BigNumber("-908570927").subtract(new BigNumber("-50890")).toString(), "-908520037");
		
		// Subtracting two negative numbers results in a positive number if the first is greater than the second
		assertEquals(new BigNumber("-872").subtract(new BigNumber("-817364")).toString(), "816492");
		
		// Subtracting two positive numbers results in a positive number if the first is greater than the second
		assertEquals(new BigNumber("65980").subtract(new BigNumber("19837")).toString(), "46143");
		
		// Subtracting two positive numbers results in a negative number is the first is less than the second
		assertEquals(new BigNumber("60").subtract(new BigNumber("70")).toString(), "-10");
		
		// Subtracting numbers with different signs results in the correct value
		assertEquals(new BigNumber("800").subtract(new BigNumber("-1234")).toString(), "2034");

	}

	@Test
	void testNegate() {
		// A positive number negated = the negative value of that number
		assertEquals(new BigNumber("89717637").negate().toString(), "-89717637");
		assertEquals(new BigNumber("0000001").negate().toString(), "-1");
		assertEquals(new BigNumber("999999").negate().toString(), "-999999");
		
		// A negative number negated = the positive value of that number
		assertEquals(new BigNumber("-95743827").negate().toString(), "95743827");
		assertEquals(new BigNumber("-0000001").negate().toString(), "1");
		assertEquals(new BigNumber("-999999").negate().toString(), "999999");
	}

	@Test
	void testEquals() {
		// Leading zeroes in input are not counted as significant digits
		assertTrue(new BigNumber("0900").equals(new BigNumber("900")));
		// The constructor only prunes zeroes until one is left
		assertTrue(new BigNumber("000").equals(new BigNumber("0")));
		assertTrue(new BigNumber("0").equals(new BigNumber("0")));
		
		// Two numbers that are not equal return false
		assertFalse(new BigNumber("-900").equals(new BigNumber("900")));
		assertFalse(new BigNumber("936515").equals(new BigNumber("13074")));
		assertFalse(new BigNumber("-5142").equals(new BigNumber("-194951")));
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	void testSign() {
		// Negative numbers return a negative sign (-1)
		assertEquals(new BigNumber("-10").sign(), -1);
		assertEquals(new BigNumber("-50928092840881").sign(), -1);
		
		// Positive numbers return a positive sign (1)
		assertEquals(new BigNumber("10").sign(), 1);
		assertEquals(new BigNumber("992871761654054987409").sign(), 1);
		assertEquals(new BigNumber("2168211218041261").sign(), 1);
	}
	
	@Test
	void testSignImmutable() {
		// The sign function does not change the contents of the BigNumber
		BigNumber x = new BigNumber("2168211218041261");
		assertEquals(x.sign(), 1);
		assertEquals(x.sign(), 1);
		assertEquals(x.sign(), 1);
		
		BigNumber y = new BigNumber("-15689");
		assertEquals(y.sign(), -1);
		assertEquals(y.sign(), -1);
		assertEquals(y.sign(), -1);
		
	}

}
