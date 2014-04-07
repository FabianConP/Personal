package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10066_The_Twin_Towers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int t = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] ab = readInts(line);
			if (ab[0] == 0 && ab[1] == 0)
				break;
			int ans = 0;
			int[] a = readInts(in.readLine());
			int[] b = readInts(in.readLine());
			if (a.length > b.length)
				ans = LCS(a, b);
			else
				ans = LCS(b, a);
			out.append("Twin Towers #" + t++ + "\nNumber of Tiles : " + ans
					+ "\n\n");

		}
		System.out.print(out);
	}

	public static int LCS(int[] x, int[] y) {
		int[][] dp = new int[x.length + 1][y.length + 1];
		int n = x.length;
		int m = y.length;
		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < m + 1; j++)
				if (i == 0 || j == 0)
					dp[i][j] = 0;
				else if (x[i - 1] == y[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
		return dp[x.length][y.length];
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
