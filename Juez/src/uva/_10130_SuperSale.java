package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _10130_SuperSale {

	static class Item {
		int p, w;

		public Item(int[] pw) {
			this.p = pw[0];
			this.w = pw[1];
		}

		@Override
		public String toString() {
			return "Item [p=" + p + ", w=" + w + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int t = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine().trim());
			Item[] items = new Item[n];
			for (int j = 0; j < n; j++)
				items[j] = new Item(readInts(in.readLine()));
			int g = Integer.parseInt(in.readLine());
			int groups[] = new int[g], MAX_W = 0;
			for (int j = 0; j < g; j++) {
				groups[j] = Integer.parseInt(in.readLine().trim());
				MAX_W = Math.max(MAX_W, groups[j]);
			}
			long[][] dp = new long[n + 1][MAX_W + 1];
			Arrays.fill(dp[0], -1);
			dp[0][0] = 0;
			long[] bestCol = new long[MAX_W + 1];
			for (int r = 1; r <= n; r++) {
				dp[r-1][0] = 0;
				for (int c = 0; c <= MAX_W; c++) {
					dp[r][c] = dp[r-1][c];
					if (c>=items[r - 1].w && dp[r - 1][c - items[r - 1].w] != -1)
						dp[r][c] = Math.max(dp[r][c], dp[r - 1][c- items[r - 1].w]+ items[r - 1].p);
					bestCol[c] = Math.max(bestCol[c], dp[r][c]);
				}
			}
			long ans = 0, theBest = 0;
			long[] best = new long[MAX_W+1];
			for (int j = 0; j < best.length; j++)
				best[j] = (theBest= Math.max(theBest, bestCol[j]));
			for (int j = 0; j < groups.length; j++) 
				ans += best[groups[j]];
			out.append(ans + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}