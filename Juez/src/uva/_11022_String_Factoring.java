package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11022_String_Factoring {

	static int dp[][], n;
	static char[] w;

	static int solve(int a, int b) {
		if (a >= n)
			return 0;
		if (dp[a][b] != 0)
			return dp[a][b];
		if (a == b)
			return dp[a][b] = 1;
		int r = b - a + 1;
		for (int i = a; i <= b; i++)
			for (int l = 1; l <= i - a + 1; l++) 
				if (check(a, i, l)) {
					if (i == b && l == (b - a + 1))
						continue;
					r = Math.min(r, solve(a, a + l - 1) + solve(i + 1, b));
				}
		return dp[a][b] = r;
	}

	static boolean check(int a, int b, int l) {
		if ((b - a + 1) % l != 0)
			return false;
		int pos = 0;
		for (int i = a + l; i <= b; i++) {
			if (w[a + pos] != w[i])
				return false;
			pos = (pos + 1) % l;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			if ((line = line.trim()).equals("*"))
				break;
			w = line.trim().toCharArray();
			n = w.length;
			dp = new int[n][n];
			int ans = solve(0, n - 1);
			out.append(ans + "\n");
		}
		System.out.print(out);
	}

}
