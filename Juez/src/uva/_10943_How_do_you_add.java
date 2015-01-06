package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10943_How_do_you_add {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int MOD = 1000000, MAX = 101;
		int[][] dp = new int[MAX][MAX];
		dp[0][0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = dp[i - 1][0];
			for (int j = 1; j < MAX; j++)
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
		}
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nk = readInts(line);
			int n = nk[0], k = nk[1];
			if (n == 0 && k == 0)
				break;
			out.append(dp[k][n] + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
