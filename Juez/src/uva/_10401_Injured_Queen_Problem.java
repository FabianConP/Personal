package uva;
import java.io.IOException;
import java.util.Scanner;

public class _10401_Injured_Queen_Problem {

	static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

	static int n;
	static boolean[][] b;
	static long col[], dp[][];

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			char[] l = in.next().trim().toCharArray();
			int n = l.length;
			b = new boolean[n][n];
			dp = new long[n][n];
			col = new long[n];
			boolean invalid = false;
			for (int col = 0; col < l.length; col++) {
				if (l[col] == '?')
					continue;
				int pos = Character.isDigit(l[col]) ? l[col] - '1' : l[col] - 'A' + 9;
				for (int row = 0; row < n; row++)
					b[row][col] = true;
				for (int[] d : dir)
					if (pos + d[0] >= 0 && pos + d[0] < n && col + d[1] >= 0 && col + d[1] < n)
						b[pos + d[0]][col + d[1]] = true;
				b[pos][col] = false;
			}
			long ans = 0;
			for (int c = n - 1; c >= 0 && !invalid; c--) {
				for (int r = 0; r < n; r++) {
					if (b[r][c])
						continue;
					if (c == n - 1)
						dp[r][c] = 1L;
					else {
						dp[r][c] = col[c + 1];
						if (r - 1 >= 0)
							dp[r][c] -= dp[r - 1][c + 1];
						dp[r][c] -= dp[r][c + 1];
						if (r + 1 < n)
							dp[r][c] -= dp[r + 1][c + 1];
					}
					if (c == 0)
						ans += dp[r][c];
					col[c] += dp[r][c];
				}
			}
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
