
public class Driver 
{

	public static void main(String[] args) 
	{
		BigNumber x = new BigNumber("2168211218041261");
		System.out.println(x);
		
		BigNumber y = new BigNumber("-280");
		System.out.println(y);
		
		System.out.println(new BigNumber("-10"));
		System.out.println(new BigNumber("10"));
		System.out.println(new BigNumber("0"));
		System.out.println(new BigNumber("001"));
		
		System.out.println(x.sign());
		System.out.println(y.sign());
		System.out.println(y.sign());
		System.out.println(y.sign());
		System.out.println(y.sign());
		
		System.out.println(new BigNumber("0900").equals(new BigNumber("900")));
	}

}
