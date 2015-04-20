package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10100_Longest_Match {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			String first = line;
			String second = in.readLine();
			String[] f = first.split("[^a-zA-Z0-9]");
			String[] s = second.split("[^a-zA-Z0-9]");
			out.append(String.format("%2d. ", nCase++));
			if (first.length() == 0 || second.length() == 0)
				out.append("Blank!\n");
			else {
				int[][] dp = new int[f.length + 1][s.length + 1];
				for (int i = 1; i <= f.length; i++) {
					for (int j = 1; j <= s.length; j++) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
						if (f[i - 1].equals(s[j - 1]))
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
					}
				}
				out.append("Length of longest match: ");
				out.append(dp[f.length][s.length] + "\n");
			}
		}
		System.out.print(out);
	}
}
