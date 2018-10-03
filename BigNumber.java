import java.util.LinkedList;

public class BigNumber
{
   private LinkedList x;
   //Default Constructor
   public BigNumber()
   {
       LinkedList<Integer> numList = new LinkedList<>();
   }
   
   /*
    * Constructor that accepts a Integer value 
    * Converts that integer into a string and then places
    * 	 each value into a linked list based on its position
    */
   public BigNumber(Integer x){
	   LinkedList<Integer> numList = new LinkedList<>();
	   String y = x.toString();
	   
	   for(int i = 0; i < y.length(); i++) {
		   numList.add((int) y.charAt(i));
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

   public BigNumber getMod(BigNumber y) //this is how he has it in the sample driver
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
   
   public void normalize()
   {
   }
   
   public BigNumber[] factor()
   {
      //return
   }
   
}
