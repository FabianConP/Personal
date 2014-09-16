package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11151_Longest_Palindrome {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nWords = Integer.parseInt(line.trim());
			for (int t = 0; t < nWords; t++) {
				String word = in.readLine().trim();
				char[] w = word.toCharArray();
				int size = w.length;
				if (size == 0)
					out.append("0\n");
				else {
					int[][] dp = new int[size][size];
					for (int j = 0; j < size; j++)
						dp[j][j] = 1;
					for (int l = 2; l <= size; l++) {
						for (int r = 0; r < size - l + 1; r++) {
							int c = r + l - 1;
							if (w[r] == w[c] && l == 2)
								dp[r][c] = 2;
							else {
								if (w[r] == w[c])
									dp[r][c] = dp[r + 1][c - 1] + 2;
								else
									dp[r][c] = Math.max(dp[r + 1][c],
											dp[r][c - 1]);
							}
						}
					}
					out.append(dp[0][size - 1] + "\n");
				}
			}
		}
		System.out.print(out);
	}
}
