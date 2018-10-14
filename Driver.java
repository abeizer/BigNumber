
public class Driver 
{

	public static void main(String[] args) 
	{

		//
		
		//System.out.println(new BigNumber("+9"));

		System.out.println("\nEquals Test");
		System.out.println(new BigNumber("-900").equals(new BigNumber("900")));
		
		System.out.println("\nCompareTo Test");
		System.out.println(new BigNumber("900").compareTo(new BigNumber("500")));
		System.out.println(new BigNumber("-900").compareTo(new BigNumber("-500")));
		System.out.println(new BigNumber("-900").compareTo(new BigNumber("500")));
		System.out.println(new BigNumber("100").compareTo(new BigNumber("-300")));
		System.out.println(new BigNumber("100").compareTo(new BigNumber("100")));
		
		
		System.out.println("\nSubtract Test");
		BigNumber i = new BigNumber("900");
		BigNumber j = new BigNumber("-900");
		System.out.println(i.subtract(j));
		System.out.println(j.subtract(i));
		
		System.out.println(new BigNumber("-1000").subtract(new BigNumber("-15000")));

	}
}
