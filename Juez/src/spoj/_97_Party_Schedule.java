package spoj;
import java.io.IOException;
import java.util.Scanner;

public class _97_Party_Schedule {

	public static final int MAX_BUDGET = 501, MAX_PARTIES = 101;
	public static int[][] dp = new int[MAX_PARTIES][MAX_BUDGET];

	public static void clean(int n, int m) {
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= m; j++)
				dp[i][j] = 0;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int budget = in.nextInt(), parties = in.nextInt();
			if (budget == 0 && parties == 0)
				break;
			clean(parties, budget);
			dp[0][0] = 0;
			int[][] p = new int[parties][2];
			for (int i = 0; i < p.length; i++) {
				p[i][0] = in.nextInt();
				p[i][1] = in.nextInt();
			}
			int cost = 0, fun = 0;
			for (int i = 1; i <= parties; i++) {
				for (int j = 0; j <= budget; j++) {
					dp[i][j] = dp[i - 1][j];
					if (j - p[i - 1][0] >= 0) {
						dp[i][j] = Math.max(dp[i][j],dp[i - 1][j - p[i - 1][0]] + p[i - 1][1]);
						if (dp[i][j] > fun || (dp[i][j] == fun && cost > j)) {
							fun = dp[i][j];
							cost = j;
						}
					}
				}
			}
			out.append(cost + " " + fun + "\n");
		}
		System.out.print(out);
	}
}
