package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _13880_GO_FOR_DIAMONDS {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nTest = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < nTest; i++) {
			int n = Integer.parseInt(in.readLine().trim());
			int size = (n + 1) / 2, x, y;
			int[][] m = new int[size][size];
			for (int j = 0; j < n; j++) {
				int[] d = readInts(in.readLine());
				if (j < size) {
					x = j;
					y = 0;
				} else {
					x = size - 1;
					y = j - size + 1;
				}
				for (int k = 0; k < d.length; k++, x--, y++)
					m[x][y] = d[k];
			}
			int a, b;
			int[][] dp = new int[size][size];
			for (int r = 0; r < m.length; r++) {
				a = b = 0;
				for (int c = 0; c < m.length; c++) {
					if (r - 1 >= 0)
						a = dp[r - 1][c];
					if (c - 1 >= 0)
						b = dp[r][c - 1];
					dp[r][c] = Math.max(a, b) + m[r][c];
				}
			}
			out.append(dp[size - 1][size - 1] + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
