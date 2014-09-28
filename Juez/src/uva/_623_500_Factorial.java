package uva;

import java.math.BigInteger;
import java.util.Scanner;

public class _623_500_Factorial {

	public static BigInteger factorial(BigInteger n, long l){
		if(l==0)
			return new BigInteger("1");
		else
			return n.multiply(factorial(n.subtract(new BigInteger("1")), --l));
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		d: do {
			long n = scan.nextLong();
			sb.append(n).append("!\n");
			sb.append(factorial(BigInteger.valueOf(n), n)).append("\n");
		} while (scan.hasNext());
		System.out.print(sb);
	}

}
