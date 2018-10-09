
/**
 * 
 * @author Abby Beizer
 * @author David Liotta
 * 
 * Implementation of BigNumber, which represents very large integer values without the use of the BigInteger class.
 */

import java.util.LinkedList;

public class BigNumber 
{
	private LinkedList<Integer> numList;
	private static int BASE = 16; //This gives us enough of a range to deal with the number 2168211218041261, as assigned.

	/**
	 * Default Constructor for BigNumber. Initializes an empty LinkedList
	 * representing the bits of the BigNumber.
	 */
	public BigNumber() 
	{
		this.numList = new LinkedList<Integer>();
	}

	/**
	 * Constructor that initializes BigNumber with a specific value in the form of Tens complement.
	 * 
	 * @param x a String representing a decimal number
	 */
	public BigNumber(String x) 
	{
		this.numList = new LinkedList<>();
		
		// If the number is negative, we will need to remember this
		// and we must remove the negative sign so that we can parse the String into
		// our LinkedList.
		boolean isNegative = false;
		if(x.startsWith("-"))
		{
			isNegative = true;
			x = x.substring(1);
		}
		
		// Now we have a positive number in decimal form
		// Each position n of the linked list represents 10^n
		for(int i = 0; i < x.length(); i++) 
		{
			numList.add(Character.getNumericValue(x.charAt(i)));
		}
		
		// If the number was positive, we have to make sure the highest order digit is less than 5
		if(!isNegative && numList.get(0) > 5)
		{ 
			numList.addFirst(0);
		}
		else	// If the number was negative, we have to negate the value in numList
		{
			negate();
			// Then we need to make sure we know this value is negative by inserting 9
			// as the highest order digit
			numList.addFirst(9);
		}
		
	}

	public BigNumber add(BigNumber y)
	{
		return this;
	}

	public BigNumber multiply(BigNumber y) 
	{
		return this;
	}

	public BigNumber subtract(BigNumber y) 
	{
		return this;
	}

	public BigNumber divide(BigNumber y) 
	{
		return this;
	}

	public BigNumber getMod(BigNumber y) // this is how he has it in the sample driver
	{
		return this;
	}

	public void negate() 
	{

	}

	public boolean equals(BigNumber y) 
	{
		return false;
	}

	public String toString() 
	{
		//TODO: Convert the numList from 10s complement before returning
		return this.numList.toString();
	}

	public int compareTo(BigNumber y) 
	{
		return -1;
	}

	public int sign() 
	{
		return 0;
	}

	public void normalize() {
	}
	

	public BigNumber[] factor() 
	{
		return (new BigNumber[2]);
	}

}
