package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10359_Tiling {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int LIM = 251;
		BigInteger[] ans = new BigInteger[LIM];
		ans[0] = BigInteger.ONE;
		ans[1] = BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		for (int i = 2; i < LIM; i++)
			ans[i] = ans[i - 2].multiply(two).add(ans[i - 1]);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			out.append(ans[n] + "\n");
		}
		System.out.print(out);
	}
}
