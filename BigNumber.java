
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
	private LinkedList<Integer> bitList;

	/**
	 * Default Constructor for BigNumber. Initializes an empty LinkedList
	 * representing the bits of the BigNumber.
	 */
	public BigNumber() 
	{
		this.bitList = new LinkedList<>();
	}

	/**
	 * Constructor that initializes BigNumber with a specific value.
	 * 
	 * @param x
	 *            a String of bits representing a number in base 10.
	 */
	public BigNumber(String x) 
	{
		this.bitList = new LinkedList<>();

		for (int i = 0; i < x.length(); i++) 
		{
			bitList.add((int) x.charAt(i));
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

	public BigNumber negate() 
	{
		return this;
	}

	public boolean equals(BigNumber y) 
	{
		return false;
	}

	public String toString() 
	{
		return "";
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
