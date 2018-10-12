
public class Driver 
{

	public static void main(String[] args) 
	{

		try {
		
			System.out.println("Constructor Test");
			System.out.println(new BigNumber("-10"));
			System.out.println(new BigNumber("10"));
			System.out.println(new BigNumber("0"));
			System.out.println(new BigNumber("0000"));
			System.out.println(new BigNumber("008"));
			System.out.println(new BigNumber("572907810201889570779716868736534556666999999999999"));
			
			try {
				System.out.println(new BigNumber("22-99999"));
			}
			catch(InvalidFormatException e)
			{
				System.out.println("Invalid character");
			}
			
			try {
				System.out.println(new BigNumber("+9"));
			}
			catch(InvalidFormatException e)
			{
				System.out.println("Invalid character");
			}
			
			System.out.println("\nSign Test");
			BigNumber x = new BigNumber("2168211218041261");
			BigNumber y = new BigNumber("-15689");
			System.out.println(x.sign());
			System.out.println(x.sign());
			System.out.println(x.sign());
			System.out.println(y.sign());
			System.out.println(y.sign());
			System.out.println(y.sign());
			
			System.out.println("\nEquals Test");
			System.out.println(new BigNumber("0900").equals(new BigNumber("900")));
			System.out.println(new BigNumber("000").equals(new BigNumber("0")));
			System.out.println(new BigNumber("0").equals(new BigNumber("")));
			System.out.println(new BigNumber("9100").equals(new BigNumber("0900")));
			
			System.out.println("\nCompareTo Test");
			System.out.println(new BigNumber("900").compareTo(new BigNumber("500")));
			System.out.println(new BigNumber("-900").compareTo(new BigNumber("-500")));
			System.out.println(new BigNumber("-900").compareTo(new BigNumber("500")));
			System.out.println(new BigNumber("100").compareTo(new BigNumber("-300")));
			System.out.println(new BigNumber("100").compareTo(new BigNumber("100")));
		}
		catch(InvalidFormatException ife)
		{
			
		}
	}

}
