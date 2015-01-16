package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _10827_Maximum_sum_on_a_torus {
	static int dp[][], m[][], area;

	public static void clearDP(int r, int c, int size) {
		dp[r][c] = m[r][c];
		area = Math.max(area, dp[r][c]);
		for (int i = 1; i < size; i++) {
			dp[r + i][c] = dp[r + i - 1][c] + m[r + i][c];
			area = Math.max(area, dp[r + i][c]);
		}
		for (int j = 1; j < size; j++) {
			dp[r][c + j] = dp[r][c + j - 1] + m[r][c + j];
			area = Math.max(area, dp[r][c + j]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		dp = new int[150][150];
		int t = Integer.parseInt(in.readLine().trim());
		for (int tt = 0; tt < t; tt++) {
			int size = Integer.parseInt(in.readLine().trim());
			m = new int[size * 2][size * 2];
			for (int i = 0; i < size; i++) {
				int[] row = readInts(in.readLine());
				for (int j = 0; j < row.length; j++)
					m[i][j] = m[i][j + size] = m[i + size][j] = m[i + size][j
							+ size] = row[j];
			}
			area = 0;
			for (int r = 0; r < size; r++)
				for (int c = 0; c < size; c++) {
					clearDP(r, c, size);
					for (int i = 1; i < size; i++)
						for (int j = 1; j < size; j++) {
							dp[r + i][c + j] = dp[r + i - 1][c + j]
									+ dp[r + i][c + j - 1]
									- dp[r + i - 1][c + j - 1]
									+ m[r + i][c + j];
							area = Math.max(area, dp[r + i][c + j]);
						}
				}
			out.append(area + "\n");
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
