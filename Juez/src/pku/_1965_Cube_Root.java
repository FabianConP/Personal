package pku;
import java.math.BigDecimal;
import java.util.Scanner;

public class _1965_Cube_Root {

	public static final BigDecimal eps= new BigDecimal("0.0000000001");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BigDecimal num;
		while (scan.hasNext()) {
			num = scan.nextBigDecimal();
			num = cubeRoot(num);
			String ans = num.toString();
			int index = ans.indexOf(".");
			ans = ans.substring(0,index+11);
			char[] a =ans.toCharArray();
			long sum = 0;
			for (int i = 0; i < a.length; i++)
				if (Character.isDigit(a[i]))
					sum += (a[i] - '0');
			System.out.println(sum % 10 + " " + ans);
		}
	}

	public static BigDecimal cubeRoot(BigDecimal num) {
		BigDecimal x, three = new BigDecimal("3"), two = new BigDecimal("2");
		x = num.divide(two);
		do {
			x = x.subtract(x.pow(3).subtract(num).divide(x.pow(2).multiply(three), 111, BigDecimal.ROUND_DOWN));
		} while ((num.subtract(x.pow(3))).abs().compareTo(eps) > 0);
		return x.abs();
	}
}
