package uva;

import java.math.BigInteger;
import java.util.Scanner;

public class _324_Factorial_Frequencies {

	public static BigInteger factorial(BigInteger n, long l) {
		if (l == 0)
			return new BigInteger("1");
		else
			return n.multiply(factorial(n.subtract(new BigInteger("1")), --l));
	}

	public static StringBuilder freq(String num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			String aux = String.valueOf(i);
			int can = num.length() - num.replaceAll(aux, "").length();
			int n = String.valueOf(can).length();
			aux = "     ";
			aux = aux.substring(0,aux.length()-n);
			//sb.append(aux);
			sb.append("   (").append(i).append(")").append(aux).append(can);
			if (i == 4)
				sb.append("\n");
		}
		return sb;    
	}   

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		d: do {
			int n = scan.nextInt();
			if (n == 0)
				break d;
			String num = factorial(new BigInteger(String.valueOf(n)), n)
					.toString();
			sb.append(n).append("! --\n");
			sb.append(freq(num)).append("\n");
		} while (scan.hasNext());
		System.out.print(sb);
	}

}
