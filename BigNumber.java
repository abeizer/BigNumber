
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
	 * @author Abby Beizer
	 * Constructor that initializes BigNumber with a specific value in the form of Tens complement.
	 * 
	 * @param x a String representing a decimal number
	 */
	public BigNumber(String x)
	{
		// Reject input that has non-numerical characters.
		// (Excludes the first character, which can be a negative sign)
		if(!x.matches("^[0-9\\-][0-9]*$"))
		{
			try 
			{
				throw new InvalidFormatException(x);
			}
			catch(InvalidFormatException e)
			{
				e.printStackTrace();
			}
			
		}
		
		this.numList = new LinkedList<>();
		
		// If the number is negative, we will need to remember this
		// and we must remove the negative sign so that we can dump the String into
		// our LinkedList.
		boolean isNegative = false;
		if(x.startsWith("-"))
		{
			isNegative = true;
			x = x.substring(1);
		}
		
		// Remove any leading zeroes from the user's input so they do not contribute to
		// errors in equals() and compareTo().
		while(x.startsWith("0") && x.length() > 1)
		{
			x = x.substring(1);
		}
		
		// Now we have a positive number in decimal form
		// Each position n of the linked list represents 10^n
		for(int i = 0; i < x.length(); i++) 
		{
			numList.add(Character.getNumericValue(x.charAt(i)));
		}
		
		// Insert a leading zero to signify that this number is positive.
		if(!isNegative)
		{
			numList.addFirst(0);
		}
		// Insert a 9 to signify that this number is negative
		else
		{
			numList = tensComplement(numList);
			numList.addFirst(9);
		}
		
	}

	/**
	 * @author Abby Beizer
	 * @param bn A value to add to this BigNumber
	 * @return the result of the addition
	 */
	public BigNumber add(BigNumber bn)
	{
		LinkedList<Integer> addition = new LinkedList<>();
		StringBuilder s = new StringBuilder();
		LinkedList<Integer> x = new LinkedList<>();
		LinkedList<Integer> y = bn.toList();
		int carry = 0;
		
		// First we need to see whether the lists are the same length.
		if(numList.size() == y.size())
		{
			// If the lists are already the same size, we need to add 
			// a single fill number to both of them
			x = fill(numList, 1);
			y = fill(y, 1);
		}
		else if(y.size() >= numList.size())
		{
			x = fill(numList, y.size() - numList.size() + 1);
			y = fill(y, 1);
		}
		else
		{
			y = fill(y, numList.size() - y.size() + 1);
			x = fill(numList, 1);
		}
		
		
		// Going from lowest order digit to highest
		for(int i = x.size() - 1; i >= 0; i--)
		{
			int temp = 0;
			
			// Add the two numbers. Record any carry.
			temp = x.get(i) + y.get(i) + carry;
			
			// After adding the numbers in the same position, we account for
			// any number greater than 9 which would cause us to carry a 1
			// to the next position's addition.
			if(temp > 9)
			{
				carry = 1;
				temp -= 10;
			}
			else
			{
				carry = 0;
			}
			addition.addFirst(temp);
		}
		
		
		// Before we convert our final result to a BigNumber, we need to make sure
		// that we are passing a format that the constructor expects. In this case,
		// the constructor wants a decimal number with a negative sign if applicable.
		// Therefore, if the result is negative, we need to skip the sign digit and
		// replace it with "-". If the number is positive, we simply skip the sign digit.
		if(addition.get(0) > 4)
		{
			addition = tensComplement(addition);
			for(int k = 1; k < addition.size(); k++)
			{
				s.append(addition.get(k));
			}
			s.insert(0, "-");
		}
		else
		{
			for(int k = 0; k < addition.size(); k++)
			{
				s.append(addition.get(k));
			}
		}
		
		return new BigNumber(s.toString());
	}

	/**
	 * 
	 * @param num
	 * @param amount
	 * @return
	 */
	private LinkedList<Integer> fill(LinkedList<Integer> num, int amount)
	{
		LinkedList<Integer> temp = new LinkedList<>();
		for(Integer item : num)
		{
			temp.add(item);
		}
		
		int fill = temp.get(0) < 5 ? 0 : 9;
		
		for(int i = 0; i < amount; i++)
		{
			temp.addFirst(fill);
		}
		
		return temp;
	}

	public BigNumber multiply(BigNumber y) 
	{
		return this;
	}

	public BigNumber subtract(BigNumber y) 
	{
		
		return (add(y.negate()));
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
	 * @author Abby Beizer
	 * Negate the value stored in BigNumber using tens complement. This does not alter the contents of BigNumber.
	 * @return A BigNumber equivalent to the negated value
	 */
	public BigNumber negate() 
	{

		StringBuilder s = new StringBuilder();
		
		// If the number is positive, then the negated value is simply the positive
		// value with a negative sign.
		if(numList.get(0) == 0)
		{
			s.append("-");
			for(int i = 1; i < numList.size(); i++)
			{
				s.append(numList.get(i));
			}
		}
		// If the value is negative, then we can find the tens complement and this will
		// be the positive value.
		else
		{
			LinkedList<Integer> temp = tensComplement(numList);
			for(int i = 1; i < temp.size(); i++)
			{
				s.append(temp.get(i));
			}
		}
		
		return new BigNumber(s.toString());
	}
	
	/**
	 * Computes the tens complement of the BigNumber and returns it as a linked list.
	 * @return The tens complement of the given number
	 */
	private LinkedList<Integer> tensComplement(LinkedList<Integer> num)
	{
		// Make a copy of numList
		// This way, we are not changing the value of numList when we negate
		// which will be useful in other methods that would otherwise require
		// reversing this negation after performing operations.
		LinkedList<Integer> temp = new LinkedList<>();
		for(Integer item : num)
		{
			temp.add(item);
		}
		
		boolean trailingZero = true;	// Whether we are currently looking at a trailing 0
		
		for(int i = temp.size() - 1; i >= 0; i--)
		{
			// If we encounter a zero and it is a trailing zero, then we do nothing to it
			// If it is not a trailing zero, then we must replace it with a 9.
			if(temp.get(i) == 0)
			{
				if(!trailingZero)
				{
					temp.set(i, 9);
				}
			}
			else
			{
				// If this is the first non-zero integer, then we subtract it from 10. If not, then subtract from 9.
				temp.set(i, (trailingZero ? 10 : 9) - temp.get(i));
				trailingZero = false;
			}
		}
		return temp;
	}

	/**
	 * @author Abby Beizer
	 * @param y Another BigNumber to compare with
	 * @return true if both BigNumbers are equal
	 */
	public boolean equals(BigNumber y) 
	{
		LinkedList<Integer> numY = y.toList();
		
		// If the two numbers do not have the same number of digits, then they are not equal
		// The constructor trims any leading zeroes in the user's input so that 
		// non-significant leading zeroes (ie. ones that do not correspond to sign) do not
		// have an impact on the length of numList. Else 00900 would not be equal to 0900, but
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
	 * @author Abby Beizer
	 * This method is needed in order to compare the values of two BigNumbers
	 * @return the value of BigNumber as a linked list
	 */
	public LinkedList<Integer> toList()
	{
		return this.numList;
	}

	/**
	 * @author Abby Beizer
	 * @return the value of BigNumber as a String
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		LinkedList<Integer> temp = numList;
		
		//If value is negative, make sure to negate first and return with a "-" sign
		if(numList.get(0) == 9)
		{
			temp = tensComplement(temp);
			s.append("-");
		}
		
		//Ignore the highest order integer, because it only represents the sign.
		for(int i = 1; i < temp.size(); i++)
		{
			s.append(temp.get(i));
		}

		return s.toString();
	}

	/**
	 * @author Abby Beizer
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
			if(xSign > ySign) 	// sign of x is positive and sign of y is negative
			{
				return 1;
			}
			else				// sign of y is positive and sign of x is negative
			{
				return -1;
			}
		}
		// The sign of each number is the same. We can try comparing the length of each number.
		else if(xLen != yLen)	
		{
			if(xLen > yLen)	// x has more digits than y
			{
				return 1;
			}
			else			// y has more digits than x
			{
				return -1;
			}
		}
		// The sign AND length of each number is the same
		else
		{
			// compare digit by digit, ignoring the sign digit.
			// if the loop finishes, then they are equal. if the loop breaks early, compare the different digits to see which was larger
			for(int i = 1; i < xLen; i++)
			{
				if(numList.get(i) > numY.get(i))		// x > y
				{
					if(xSign == 1)	//both positive
					{
						return 1;
					}
					return -1;		//both negative
				}
				else if(numList.get(i) < numY.get(i))	// y > x
				{
					if(xSign == 1)	//both positive
					{
						return -1;
					}
					return 1;		//both negative
				}	
			}
			return 0;
		}// end if
	}

	/**
	 * @author Abby Beizer
	 * @return Whether the number is positive (1) or negative (-1)
	 */
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
