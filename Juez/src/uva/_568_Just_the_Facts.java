package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _568_Just_the_Facts {

	public static BigInteger[] fact;

	public static void fillFibo(int size) {
		fact = new BigInteger[size + 1];
		fact[0] = fact[1] = BigInteger.ONE;
		for (int i = 2; i < fact.length; i++)
			fact[i] = fact[i - 1].multiply(new BigInteger(i + ""));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillFibo(10000);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line);
			out.append(format(n) + " -> " + last(fact[n]) + "\n");
		}
		System.out.print(out);
	}

	public static String format(int n) {
		String ans = n + "";
		return "     ".substring(0, 5 - ans.length()) + ans;
	}

	public static char last(BigInteger n) {
		char[] ans = n.toString().toCharArray();
		for (int i = ans.length - 1; i >= 0; i--)
			if (ans[i] != '0')
				return ans[i];
		return '1';
	}
}
