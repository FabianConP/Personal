package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _1172_The_Bridges_of_Kolsberg {

	static int n, m;
	static int[] ida, idb;
	static long[][] dp, br;
	static int[] va, vb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			HashMap<String, Integer> ids = new HashMap<>();
			n = Integer.parseInt(in.readLine().trim());
			ida = new int[n];
			va = new int[n];
			int id = 0;
			for (int i = 0; i < n; i++) {
				String[] v = in.readLine().split(" ");
				if (!ids.containsKey(v[1]))
					ids.put(v[1], id++);
				ida[i] = ids.get(v[1]);
				va[i] = Integer.parseInt(v[2]);
			}
			m = Integer.parseInt(in.readLine().trim());
			idb = new int[m];
			vb = new int[m];
			for (int i = 0; i < m; i++) {
				String[] v = in.readLine().split(" ");
				if (!ids.containsKey(v[1]))
					ids.put(v[1], id++);
				idb[i] = ids.get(v[1]);
				vb[i] = Integer.parseInt(v[2]);
			}
			dp = new long[n + 1][m + 1];
			br = new long[n + 1][m + 1];

			long curf = 0, curb = 0;
			long best = 0, bridges = 0;

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					curf = curb = 0;
					if (curf < (dp[i][j - 1]) || (curf == dp[i][j - 1] && curb < br[i][j - 1])) {
						curf = dp[i][j - 1];
						curb = br[i][j - 1];
					}
					if (curf < dp[i - 1][j] || (curf == dp[i - 1][j] && curb < br[i - 1][j])) {
						curf = dp[i - 1][j];
						curb = br[i - 1][j];
					}
					if (ida[i - 1] == idb[j - 1])
						if (curf < (dp[i - 1][j - 1] + va[i - 1] + vb[j - 1])
								|| (curf == (dp[i - 1][j - 1] + va[i - 1] + vb[j - 1])
										&& curb < (br[i - 1][j - 1] + 1))) {
							curf = dp[i - 1][j - 1] + va[i - 1] + vb[j - 1];
							curb = br[i - 1][j - 1] + 1;
						}
					dp[i][j] = curf;
					br[i][j] = curb;
					if (best < dp[i][j] || (best == dp[i][j] && bridges > br[i][j])) {
						best = dp[i][j];
						bridges = br[i][j];
					}
				}
			}

			out.append(best + " " + bridges + "\n");
		}
		System.out.print(out);
	}
}
