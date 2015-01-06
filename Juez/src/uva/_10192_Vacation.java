package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10192_Vacation {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			if (line.trim().startsWith("#"))
				break;
			char[] a = line.toCharArray();
			char[] b = in.readLine().toCharArray();
			int[][] dp = new int[a.length + 1][b.length + 1];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < b.length; j++) {
					if (a[i] == b[j])
						dp[i + 1][j + 1] = dp[i][j] + 1;
					else
						dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
			out.append("Case #" + nCase++ + ": you can visit at most "
					+ dp[a.length][b.length] + " cities.\n");

		}
		System.out.print(out);
	}
}
