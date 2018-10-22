
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

		/**
	 * @author David Liotta
	 * Parse through each number in this's numlist and multiply it to each number y's numList
	 * Add that result to an ever growing product and when done, return the product
	 * Also does some negation checking
	 * @param y : the number being multiplied to this
	 * @return product
	 */	
	public BigNumber multiply(BigNumber y) {
		//Setting up some local variables as to not change the real values of this and 
		BigNumber product = new BigNumber("0");
		BigNumber in = this;
		BigNumber param = y;
		
		//Checking for if either numbers were negative and making them positive
		if(in.numList.get(0) != 0)
			in = in.negate();
		if(param.numList.get(0) != 0)
			param = param.negate();
		
		//keeping count since we loop through the list from top to bottom
		int jc = 0;	
		int ic = 0;
		// We want to loop through each number in the both arrays from the lowest digit to highest 
		// and multiply each to each other. Then add that result to the product
		for(int i = in.numList.size() - 1; i > 0; i--) {
			for(int j = param.numList.size() - 1; j > 0; j--) {
				//Weed out any values that will just be 0 and skip to next pair
				if(in.numList.get(i) != 0 && param.numList.get(j) != 0) {
					int tNum = in.numList.get(i) * param.numList.get(j);
					//Adds zeros to the end of the number to account for magnitude
					String zeros = "";
					for(int k = 0; k < jc + ic;k++)
						zeros = zeros +  "0";
					String temp = tNum + zeros;
					BigNumber tempBN = new BigNumber(temp);
					product = product.add(tempBN);
				}
				jc++;
			}
			jc = 0;
			ic++;
		}
		
		//If only one was negative and the other is not, return a negative product
		if(this.numList.get(0) == 0 && y.numList.get(0) != 0)
			return product.negate();
		if(this.numList.get(0) != 0 && y.numList.get(0) == 0)
			return product.negate();
		
		return product;
	}


	public BigNumber subtract(BigNumber y) 
	{
		
		return (add(y.negate()));
	}

		/**
	 * @author David Liotta
	 * divides one BigNumber by another BigNumber
	 * @param y : the divisor that is dividing this
	 * @return the quotient rounded down
	 */
	public BigNumber divide(BigNumber y) {
		BigNumber quotient = new BigNumber("0");
		BigNumber rem = this;
		BigNumber div = y;
		
		//These will cut down on time later by a lot
		BigNumber one = new BigNumber("1"); 
		BigNumber onek = new BigNumber("1000");
		BigNumber onemil = new BigNumber("1000000");
		BigNumber onebil = new BigNumber("1000000000");
		BigNumber onetril = new BigNumber("1000000000000");
		BigNumber onequat = new BigNumber("1000000000000000");
		
		//If they are negative, make positive to make operations easier
		if(rem.numList.get(0) != 0)
			rem = rem.negate();
		if(div.numList.get(0) != 0)
			div = div.negate();
		
		//While checking to make sure the remainder is still larger than the divisor
		//Subtract the remainder by the divisor times the appropriate magnitude
		while(rem.compareTo(div) == 1) {
			if(rem.numList.size() > div.numList.size() + 15) {
				rem = rem.subtract(div.multiply(onequat));
				quotient = quotient.add(onequat);
			}else if(rem.numList.size() > div.numList.size() + 12) {
				rem = rem.subtract(div.multiply(onetril));
				quotient = quotient.add(onetril);
			}else if(rem.numList.size() > div.numList.size() + 9) {
				rem = rem.subtract(div.multiply(onebil));
				quotient = quotient.add(onebil);
			}else if(rem.numList.size() > div.numList.size() + 6) {
				rem = rem.subtract(div.multiply(onemil));
				quotient = quotient.add(onemil);
			}else if(rem.numList.size() > div.numList.size() + 3) {
				rem = rem.subtract(div.multiply(onek));
				quotient = quotient.add(onek);
			}else{
				rem = rem.subtract(div);
				quotient = quotient.add(one);
			}
		}
		
		//If only one was negative and the other is not, return a negative quotient
		if(this.numList.get(0) == 0 && y.numList.get(0) != 0)
			return quotient.negate();
		if(this.numList.get(0) != 0 && y.numList.get(0) == 0)
			return quotient.negate();
		
		return quotient;
	}

	/**
	 * @author David Liotta
	 * @param y : the modulus operator operation on this
	 * @return the remainder
	 * We can assume all input into this function
	 * mod and division do basically the same thing except mod returns the remainder instead of quotient
	 */
	public BigNumber getMod(BigNumber y){
		BigNumber rem = this;
		BigNumber div = y;
		
		//These will cut down on time later by a lot
		BigNumber onek = new BigNumber("1000");
		BigNumber onemil = new BigNumber("1000000");
		BigNumber onebil = new BigNumber("1000000000");
		BigNumber onetril = new BigNumber("1000000000000");
		BigNumber onequat = new BigNumber("1000000000000000");
		
		//While checking to make sure the remainder is still larger than the divisor
		//Subtract the remainder by the divisor times the appropriate magnitude
		while(rem.compareTo(div) == 1) {
			if(rem.numList.size() > div.numList.size() + 15) 
				rem = rem.subtract(div.multiply(onequat));
			else if(rem.numList.size() > div.numList.size() + 12) 
				rem = rem.subtract(div.multiply(onetril));
			else if(rem.numList.size() > div.numList.size() + 9) 
				rem = rem.subtract(div.multiply(onebil));
			else if(rem.numList.size() > div.numList.size() + 6) 
				rem = rem.subtract(div.multiply(onemil));
			else if(rem.numList.size() > div.numList.size() + 3) 
				rem = rem.subtract(div.multiply(onek));
			else
				rem = rem.subtract(div);
		}
		return rem;
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
			if(xLen > yLen)			// x has more digits than y
			{
				if(xSign == -1)		// a larger negative value = 'more negative' than the smaller
				{
					return -1;
				}
				return 1;
			}
			else					// y has more digits than x
			{
				if(ySign == -1)		// a smaller negative value = 'less negative' than the larger
				{
					return 1;
				}
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
