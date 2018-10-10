
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
		
		//TODO: Remove any leading zeroes from the user's input so they do not contribute to
		// errors in equals() and compareTo().
		
		// Now we have a positive number in decimal form
		// Each position n of the linked list represents 10^n
		for(int i = 0; i < x.length(); i++) 
		{
			numList.add(Character.getNumericValue(x.charAt(i)));
		}
		
		// If the number was positive, insert a leading 0.
		if(!isNegative)
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

	/**
	 * Negate the value stored in BigNumber
	 */
	public BigNumber negate() 
	{
		boolean trailingZero = true;	// Whether we are currently looking at a trailing 0
		
		for(int i = numList.size() - 1; i >= 0; i--)
		{
			// If we encounter a zero and it is a trailing zero, then we do nothing to it
			// If it is not a trailing zero, then we must replace it with a 9.
			if(numList.get(i) == 0)
			{
				if(!trailingZero)
				{
					numList.set(i, 9);
				}
			}
			else
			{
				// If this is the first non-zero integer, then we subtract it from 10. If not, then subtract from 9.
				numList.set(i, (trailingZero ? 10 : 9) - numList.get(i));
				trailingZero = false;
			}
		}
		
		return this;
	}

	/**
	 * 
	 * @param y Another BigNumber to compare with
	 * @return true if both BigNumbers are equal
	 */
	public boolean equals(BigNumber y) 
	{
		LinkedList<Integer> numY = y.toList();
		
		// If the two numbers do not have the same number of digits, then they are not equal
		// The constructor should trim any leading zeroes in the user's input so that 
		// non-significant leading zeroes (ie. ones that do not correspond to sign) do not
		// have an impact on the length of numList. Else 00900 would not be equal to 0900, even though
		// it technically should be.
		if(numY.size() == numList.size())
		{
			for(int i = 0; i < numList.size(); i++)
			{
				if(numList.get(i) != numY.get(i))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is needed in order to compare the values of two BigNumbers
	 * @return the value of BigNumber as a linked list
	 */
	public LinkedList<Integer> toList()
	{
		return this.numList;
	}

	/**
	 * @return the value of BigNumber as a String
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		LinkedList<Integer> temp = numList;
		
		//If value is negative, make sure to negate first and return with a "-" sign
		if(numList.get(0) == 9)
		{
			negate();
			s.append("-");

			// Store the result of the negation so that we can use it, then
			// negate numList again so that the contents of this object do not change
			temp = numList;
			negate();
		}
		
		//Ignore the highest order integer, because it only represents the sign.
		for(int i = 1; i < temp.size(); i++)
		{
			s.append(temp.get(i));
		}

		return s.toString();
	}

	/**
	 * Compares the BigNumber value against another BigNumber value.
	 * @param y A BigNumber to compare against
	 * @return -1 if compared against a larger number, 0 if equal, or 1 if compared against a smaller number
	 */
	public int compareTo(BigNumber y) 
	{
		LinkedList<Integer> numY = y.toList();
		
		// We can tell right away if one number is larger than the other if their signs are different
		int xSign = sign(), ySign = y.sign();
		int xLen = numList.size(), yLen = numY.size();
		if(xSign != ySign) 
		{
			if(xSign > ySign) // sign of x is positive and sign of y is negative
			{
				return 1;
			}
			else	// sign of y is positive and sign of x is negative
			{
				return -1;
			}
		}
		// The sign of each number is the same. We can try comparing the length of each number.
		else if(xLen != yLen)	
		{
			if(xLen > yLen)	// this has more digits than y
			{
				return 1;
			}
			else	// y has more digits than x
			{
				return -1;
			}
		}
		// The sign AND length of each number is the same
		else
		{
			//TODO: finish
			// compare digit by digit. if the loop finishes, then they are equal. if the loop breaks early, compare the different digits to see which was larger
			return -1;
		}
	}

	public int sign() 
	{
		return (numList.get(0) < 5 ? 1 : -1);
	}

	public void normalize() {
	}
	

	public BigNumber[] factor() 
	{
		return (new BigNumber[2]);
	}

}
