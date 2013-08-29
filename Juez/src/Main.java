import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

	public static BigDecimal eps = BigDecimal.TEN;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BigDecimal num;
		for (int i = 0; i < 300; i++)
			eps = eps.divide(BigDecimal.TEN, RoundingMode.HALF_UP);
		while (scan.hasNext()) {
			num = new BigDecimal(scan.nextBigInteger());
			num = NewtonRaphson(num);
			char[] a = num.setScale(11, RoundingMode.FLOOR)
					.toPlainString().toCharArray();
			long sum = 0;
			for (int i = 0; i < a.length-1; i++) 
				if(Character.isDigit(a[i]))
					sum+=(a[i]-'0');
			String ans = num.setScale(11, RoundingMode.FLOOR)
					.toPlainString();
			System.out.println(sum%10+" "+ans.substring(0,ans.length()-1));
		}
	}

	public static BigDecimal NewtonRaphson(BigDecimal num) {
		BigDecimal ans = f(
				num.divide(new BigDecimal("3"), 8, RoundingMode.HALF_UP), num), ef, eff;
		for (int i = 0; i < 500; i++) {
			ef = f(ans, num);
			eff = ff(ans);
			if (eff.compareTo(eps) <= 0)
				break;
			ans = ans.subtract(ef.divide(eff, 40, RoundingMode.HALF_UP));
		}
		return ans;
	}

	public static BigDecimal f(BigDecimal x, BigDecimal num) {
		return (x.pow(3)).subtract(num);
	}

	public static BigDecimal ff(BigDecimal x) {
		return (BigDecimal.valueOf(3)).multiply(x.pow(2));
	}
}
