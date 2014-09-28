package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10177_2_3_4_D_Sqr_Rects_Cubes_Boxes {
	public static BigInteger c1, r1, c2, r2, c3, r3;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			long n = Long.parseLong(line.trim());
			c1 = S1(n);
			c2 = S2(n);
			r1 = R1(n);
			r2 = R2(n);
			c3 = S3(n);
			r3 = R3(n);
			out.append(c1+" "+r1+" "+c2+" "+r2+" "+c3+" "+r3+"\n");
		}
		System.out.print(out);
	}

	public static BigInteger S1(long n) {
		return (toBI(n).multiply(toBI(n + 1)).multiply(toBI(2 * n + 1)))
				.divide(toBI(6));
	}
	
	public static BigInteger R1(long n) {
		return c2.subtract(c1);
	}

	public static BigInteger S2(long n) {
		return (toBI(n * n).multiply(toBI(n + 1).pow(2))).divide(toBI(4));
	}
	
	public static BigInteger R2(long n) {
		return ((toBI(n*n*n).multiply(toBI(n+1).pow(3))).divide(toBI(8))).subtract(c2);
	}
	
	public static BigInteger S3(long n) {
		return (c1.multiply(toBI(3*n*n+3*n-1))).divide(toBI(5));
	}
	
	public static BigInteger R3(long n) {
		return (((toBI(n).pow(4)).multiply(toBI(n+1).pow(4))).divide(toBI(16))).subtract(c3);
	}

	public static BigInteger toBI(long n) {
		return BigInteger.valueOf(n);
	}
}
