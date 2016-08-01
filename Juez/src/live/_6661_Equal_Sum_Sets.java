package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6661_Equal_Sum_Sets {

	static int n, k, s;

	static long[][][] dp;

	static long solve(int l, int prev, int acum) {
		if (l == k)
			return 1L;
		if ((s - acum) > (k - l) * n)
			return 0L;
		if (dp[l][prev][acum] != -1L)
			return dp[l][prev][acum];
		long ans = 0;
		int min = Math.min(n, s - acum - (k - l - 1) * n);
		min = Math.max(min, prev + 1);
		int max = Math.max(0, (s - acum) - (k - l - 1));
		max = Math.min(max, n);
		for (int i = min; i <= max; i++)
			ans += solve(l + 1, i, acum + i);
		return dp[l][prev][acum] = ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			int[] v = readInts(line);
			n = v[0];
			k = v[1];
			s = v[2];
			if(n == 0 && k == 0 && s == 0) break;
			dp = new long[k + 1][n + 1][s + 1];
			for (int i = 0; i <= k; i++)
				for (int j = 0; j <= n; j++)
					for (int l = 0; l <= s; l++)
						dp[i][j][l] = -1L;
			out.append(solve(0, 0, 0) + "\n");
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
