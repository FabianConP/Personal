package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _11296_Counting_Solutions_to_an_Integral_Equation {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		BigInteger one = BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int num = Integer.parseInt(line.trim()) / 2;
			BigInteger n = BigInteger.valueOf(num);
			BigInteger ans = ((n.add(one)).multiply(n.add(two))).divide(two);
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
