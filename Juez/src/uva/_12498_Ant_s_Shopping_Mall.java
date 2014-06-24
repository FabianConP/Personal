package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class _12498_Ant_s_Shopping_Mall {

	public static int INF = 1 << 20, dp[][], RC[];

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int times = 1;
		StringBuilder out = new StringBuilder();
		int nCase = in.nextInt();
		RC = new int[2];
		for (int i = 0; i < nCase; i++) {
			RC[0] = in.nextInt();
			RC[1] = in.nextInt();
			dp = new int[RC[0]][RC[1]];
			for (int j = 0; j < dp.length; j++)
				Arrays.fill(dp[j], INF);
			for (int j = 0; j < RC[0]; j++) {
				char[] row = in.next().trim().toCharArray();
				for (int k = 0; k < RC[1]; k++)
					if (row[k] == '0')
						for (int h = 0; h < RC[1]; h++)
							dp[j][h] = Math.min(dp[j][h], Math.abs(k - h));
			}

			int ans = INF, cur = 0;
			for (int j = 0; j < RC[1]; j++, cur = 0) {
				for (int k = 0; k < dp.length; k++)
					cur += dp[k][j];
				ans = Math.min(cur, ans);
			}
			out.append("Case " + times++ + ": " + (ans == INF ? -1 : ans)
					+ "\n");
		}
		System.out.print(out);
	}
}
