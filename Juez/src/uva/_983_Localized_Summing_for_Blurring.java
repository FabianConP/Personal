package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _983_Localized_Summing_for_Blurring {

	static int n, M, m[][];
	static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		m = new int[1000][1000];
		dp = new long[1001][1001];
		int nTest = 1;
		while ((line = in.readLine()) != null) {
			if (nTest != 1)
				line = in.readLine();
			int[] nm = readInts(line);
			n = nm[0];
			M = nm[1];
			for (int i = n - 1; i >= 0; i--)
				for (int j = 0; j < n; j++)
					m[i][j] = Integer.parseInt(in.readLine().trim());
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]
							+ m[i - 1][j - 1];
			if (nTest++ != 1)
				out.append("\n");
			long sum = 0, val = 0;
			for (int i = n - M + 1; i >= 1; i--)
				for (int j = 1; j <= n - M + 1; j++) {
					val = dp[i + M - 1][j + M - 1] - dp[i + M - 1][j - 1]
							- dp[i - 1][j + M - 1] + dp[i - 1][j - 1];
					out.append(val + "\n");
					sum += val;
				}
			out.append(sum + "\n");
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
