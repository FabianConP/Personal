package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.StringTokenizer;

public class _10081_Tight_Words {

	static BigInteger dp[][];
	static int k, n;

	static BigInteger solve(int d, int l) {
		if (l == n)
			return BigInteger.ONE;
		if (dp[d][l] != null)
			return dp[d][l];
		BigInteger cur = BigInteger.ZERO;
		if (d - 1 >= 0)
			cur = cur.add(solve(d - 1, l + 1));
		cur = cur.add(solve(d, l + 1));
		if (d + 1 <= k)
			cur = cur.add(solve(d + 1, l + 1));
		return dp[d][l] = cur;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			int[] nk = readInts(line);
			k = nk[0];
			n = nk[1];
			dp = new BigInteger[k + 1][n];
			BigInteger total = BigInteger.valueOf(k + 1).pow(n);
			BigInteger num = BigInteger.ZERO;
			for (int i = 0; i <= k; i++)
				num = num.add(solve(i, 1));
			BigDecimal ans = new BigDecimal(num);
			ans = ans.divide(new BigDecimal(total), 15, BigDecimal.ROUND_CEILING);
			out.append(String.format(Locale.US, "%.5f\n", ans.doubleValue() * 100.0));
		}
		System.out.print(out);
	}

	static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
