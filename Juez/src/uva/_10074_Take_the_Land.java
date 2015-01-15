package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _10074_Take_the_Land {

	static int dp[][], m[][], area;

	public static void clearDP(int r, int c, int n, int mm) {
		dp[r][c] = m[r][c];
		if(dp[r][c]==0)
			area = Math.max(area, 1);
		for (int i = r + 1; i < n; i++) {
			dp[i][c] = dp[i - 1][c] + m[i][c];
			if (dp[i][c] == 0)
				area = Math.max(area, i - r + 1);
		}
		for (int j = c + 1; j < mm; j++) {
			dp[r][j] = dp[r][j - 1] + m[r][j];
			if (dp[r][j] == 0)
				area = Math.max(area, j - c + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		dp = new int[100][100];
		while ((line = in.readLine()) != null) {
			int[] nm = readInts(line);
			if(nm[0] == 0 && nm[1] == 0)
				break;
			m = new int[nm[0]][nm[1]];
			for (int i = 0; i < nm[0]; i++)
				m[i] = readInts(in.readLine());
			area = 0;
			for (int r = 0; r < nm[0]; r++) {
				for (int c = 0; c < nm[1]; c++) {
					clearDP(r, c, nm[0], nm[1]);
					for (int i = r + 1; i < nm[0]; i++) {
						for (int j = c + 1; j < nm[1]; j++) {
							dp[i][j] = dp[i - 1][j] + dp[i][j - 1] -dp[i - 1][j - 1] + m[i][j];
							if (dp[i][j] == 0)
								area = Math.max(area, (i - r + 1) * (j - c + 1));
						}
					}
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
