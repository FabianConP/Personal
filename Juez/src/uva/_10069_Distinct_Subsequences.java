package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class _10069_Distinct_Subsequences {

	public static final int MAX_PAT = 100001, MAX_CAD = 101;
	public static BigInteger[][] dp = new BigInteger[MAX_CAD][MAX_PAT];

	public static void init() {
		for (int i = 0; i < MAX_CAD; i++)
			for (int j = 0; j < MAX_PAT; j++)
				if (i == 0)
					dp[i][j] = BigInteger.ONE;
				else
					dp[i][j] = BigInteger.ZERO;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		init();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int t = Integer.parseInt(line.trim());
			for (int i = 0; i < t; i++) {
				char[] a = in.readLine().trim().toCharArray();
				char[] b = in.readLine().trim().toCharArray();
				for (int j = 1; j <= b.length; j++)
					for (int k = 1; k <= a.length; k++)
						if (b[j - 1] == a[k - 1])
							dp[j][k] = dp[j - 1][k - 1].add(dp[j][k - 1]);
						else
							dp[j][k] = dp[j][k - 1];
				out.append(dp[b.length][a.length] + "\n");
			}
		}
		System.out.print(out);
	}
}
