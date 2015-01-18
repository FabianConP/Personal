package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _10664_Luggage {

	static int sumAll;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int t = Integer.parseInt(line);
			for (int tt = 0; tt < t; tt++) {
				sumAll = 0;
				int[] v = readInts(in.readLine());
				if (sumAll % 2 == 1)
					out.append("NO\n");
				else {
					boolean[][] dp = new boolean[2][sumAll + 1];
					dp[0][0] = dp[1][0] = true;
					for (int e = 0; e < v.length; e++)
						for (int i = 0; i <= sumAll; i++) {
							dp[(e & 1) ^ 1][i] |= dp[e & 1][i];
							if (v[e] <= i && dp[e & 1][i - v[e]])
								dp[(e & 1) ^ 1][i] = true;
						}
					out.append(dp[v.length & 1][sumAll / 2] ? "YES\n" : "NO\n");
				}
			}

		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			sumAll += (a[i] = Integer.parseInt(w[i]));
		return a;
	}
}
