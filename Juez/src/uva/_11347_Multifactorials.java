package uva;

import java.math.BigInteger;
import java.util.Scanner;

public class _11347_Multifactorials {

	public static BigInteger factorial(BigInteger n, long l) {
		if (l == 0)
			return new BigInteger("1");
		else
			return n.multiply(factorial(n.subtract(new BigInteger("1")), --l));
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int times = Integer.parseInt(scan.nextLine());
		f: for (int i = 0; i < times; i++) {
			String[] mus = scan.nextLine().split(" / ");
			BigInteger n = new BigInteger(mus[0]);
			BigInteger d = new BigInteger(mus[1]);
			BigInteger gcd = n.gcd(d);
			n = n.divide(gcd);
			d = d.divide(gcd);
			sb.append(n).append(" / ").append(d).append("\n");
		}
		System.out.print(sb);
	}

}
