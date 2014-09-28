package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _357_Let_Me_Count_The_Ways {
	public static int[] coins = { 1, 5, 10, 25, 50 };
	public static long[] dp;

	public static void diffWays(int n) {
		dp = new long[n + 1];
		dp[0] = 1;
		for (int i = 0; i < coins.length; i++)
			for (int j = coins[i]; j <= n; j++)
				dp[j] += dp[j - coins[i]];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		diffWays(8);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int change = Integer.parseInt(line);
			long ans = dp[change];
			if(ans!=1)
				sb.append("There are ").append(ans).append(" ways to produce ").append(change).append(" cents change.").append("\n");
			else
				sb.append("There is only 1 way to produce ").append(change).append(" cents change.").append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
