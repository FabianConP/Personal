package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _674_Coin_Change {
	public static int[] coins = { 1, 5, 10, 25, 50 };
	public static int[] dp;

	public static void count(int[] S, int m, int n) {
		dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 0; i < m; i++)
			for (int j = S[i]; j <= n; j++)
				dp[j] += dp[j - S[i]];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		count(coins, coins.length, 7490);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int change = Integer.parseInt(line);
			sb.append(dp[change]).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
