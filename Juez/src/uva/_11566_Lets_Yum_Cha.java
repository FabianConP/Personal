package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class _11566_Lets_Yum_Cha {

	static int n, x, t, k;
	static int f[], c[], dp[][][];

	static int solve(int budget, int dish, int amount) {
		if (dish == k)
			return 0;

		if (dp[budget][dish][amount] != -1)
			return dp[budget][dish][amount];

		int best = 0, tax = 0, spent = (n * (x - t)) - budget, remain;

		// Two
		tax = (int) Math.ceil((spent + c[dish] * 2 + t * n) * 0.1);
		remain = budget - c[dish] * 2;
		if ((remain - tax) >= 0 && (amount + 2) <= n * 2)
			best = Math.max(best, f[dish] * 2 + solve(remain, dish + 1, amount + 2));

		// One
		tax = (int) Math.ceil((spent + c[dish] + t * n) * 0.1);
		remain = budget - c[dish];
		if ((remain - tax) >= 0 && (amount + 1) <= n * 2)
			best = Math.max(best, f[dish] + solve(remain, dish + 1, amount + 1));

		// Zero
		best = Math.max(best, solve(budget, dish + 1, amount));
		return dp[budget][dish][amount] = best;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			n = in.nextInt();
			x = in.nextInt();
			t = in.nextInt();
			k = in.nextInt();
			if (n + x + t + k == 0)
				break;
			n++; // Me
			f = new int[k];
			c = new int[k];
			for (int i = 0; i < k; i++) {
				c[i] = in.nextInt();
				for (int j = 1; j <= n; j++)
					f[i] += in.nextInt();
			}
			dp = new int[n * x + 1][k][n * 2 + 1];
			for (int i = 0; i <= n * x; i++)
				for (int j = 0; j < k; j++)
					Arrays.fill(dp[i][j], -1);
			int best = solve(n * (x - t), 0, 0);
			out.append(String.format(Locale.US, "%.2f\n", (best * 1.0) / n));
		}
		System.out.print(out);
	}

}
