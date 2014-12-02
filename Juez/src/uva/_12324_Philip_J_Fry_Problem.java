package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _12324_Philip_J_Fry_Problem {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int[][] d = new int[n][2];
			for (int i = 0; i < n; i++)
				d[i] = readInts(in.readLine());
			int[][] t = new int[n][n];
			int[][] am = new int[n][n];
			Arrays.fill(t[0], d[0][0]);
			Arrays.fill(am[0], d[0][1]);
			for (int i = 1; i < n; i++) {
				t[i][0] = t[i - 1][0] + d[i][0];
				am[i][0] = am[i - 1][0] + d[i][1];
			}
			for (int i = 1; i < n; i++)
				for (int j = 1; j < n; j++) {
					t[i][j] = t[i - 1][j] + d[i][0];
					am[i][j] = am[i - 1][j] + d[i][1];
					if (am[i - 1][j - 1] > 0 && t[i - 1][j - 1] + d[i][0] / 2 < t[i][j]) {
						t[i][j] = t[i - 1][j - 1] + d[i][0] / 2;
						am[i][j] = am[i - 1][j - 1] + d[i][1] - 1;
					}
				}
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++)
				ans = Math.min(ans, t[n - 1][i]);
			out.append(ans + "\n");
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
