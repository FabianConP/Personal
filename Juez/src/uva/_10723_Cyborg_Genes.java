package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10723_Cyborg_Genes {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			String first = in.readLine().trim();
			String second = in.readLine().trim();
			int n = first.length(), m = second.length();
			if (first.isEmpty() || second.isEmpty())
				out.append("Case #" + (nCase + 1) + ": " + (n + m) + " 1\n");
			else {
				int[][] lcs = new int[n + 1][m + 1];
				int[][] ways = new int[n + 1][m + 1];
				for (int i = 0; i <= n; i++)
					ways[i][0] = 1;
				for (int i = 0; i <= m; i++)
					ways[0][i] = 1;
				for (int i = 1; i <= n; i++) 
					for (int j = 1; j <= m; j++) 
						if (first.charAt(i - 1) == second.charAt(j - 1)) {
							lcs[i][j] = lcs[i - 1][j - 1] + 1;
							ways[i][j] = ways[i - 1][j - 1];
						} else {
							lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
							if(lcs[i][j] == lcs[i - 1][j]) ways[i][j] += ways[i - 1][j];
							if(lcs[i][j] == lcs[i][j - 1]) ways[i][j] += ways[i][j - 1];
						}
				out.append("Case #" + (nCase + 1)+": " +(n + m - lcs[n][m]) + " " + ways[n][m]+"\n");
			}
		}
		System.out.print(out);
	}

}
