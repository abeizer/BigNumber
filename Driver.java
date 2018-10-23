
public class Driver 
{

	public static void main(String[] args) throws InvalidFormatException 
	{

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
		
		
		BigNumber k = new BigNumber("34567");
		BigNumber l = new BigNumber("123456789");
		BigNumber m = new BigNumber("-53859");
		BigNumber n = new BigNumber("24680135790987654321");
		BigNumber o = new BigNumber("-9038759129834287524398298740978570184324324536376347476235366");
		BigNumber p = new BigNumber("-98765");
		BigNumber md = new BigNumber("2");
		BigNumber ev = new BigNumber("150");
		BigNumber ln = new BigNumber("2168211218041261");
		BigNumber q = new BigNumber("000056");
		
		System.out.println("\nAdd Test");
		System.out.println(i.add(k));
		System.out.println(n.add(l));
		System.out.println(n.add(o));
		
		
		System.out.println("\nMultiply Test");
		System.out.println(i.multiply(k));  //900 * 34567 should equal 31110300
		System.out.println(m.multiply(k));	//-53859 * 34567 should equal -1861744053
		System.out.println(m.multiply(p));	// -53859 * -98765 should equal 5319384135
		System.out.println(k.multiply(l));	//34567 * 123456789 should equal 4267530825363
		System.out.println(o.multiply(p)); //This should be around 8.9271305e^65
		
		System.out.println("\nDivision Tests");
		System.out.println(l.divide(k));	//Should return 3571
		System.out.println(p.divide(m));	//Should return 1
		System.out.println(l.divide(p));	//Should return -1250
		System.out.println(n.divide(k));	//Should return 713979685566802

		
		System.out.println(i.divide(md));
		
		System.out.println("\nModulus Test");
		System.out.println(i.getMod(k));	//900 mod 34567 = 900
		System.out.println(n.getMod(l));	//123456789
		System.out.println(ev.getMod(md));
		
		
		System.out.println("\nNormalization Test");
		System.out.println(q);
		
		System.out.println("\nGCD Test");
		System.out.println(i.gcd(ev));
		
		System.out.println("\nFactor Test");
		System.out.println(i.factor());
		System.out.println(l.factor());
		System.out.println(p.factor());
		System.out.println(ln.factor());
		
		
	}

}
