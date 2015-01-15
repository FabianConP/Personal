package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


public class _10667_Largest_Block {
	
	static int dp[][], m[][], area;

	public static void clearDP(int r, int c, int size) {
		dp[r][c] = m[r][c];
		if(dp[r][c]==0)
			area = Math.max(area, 1);
		for (int i = r + 1; i < size; i++) {
			dp[i][c] = dp[i - 1][c] + m[i][c];
			if (dp[i][c] == 0)
				area = Math.max(area, i - r + 1);
		}
		for (int j = c + 1; j < size; j++) {
			dp[r][j] = dp[r][j - 1] + m[r][j];
			if (dp[r][j] == 0)
				area = Math.max(area, j - c + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		dp = new int[100][100];
		int t = Integer.parseInt(in.readLine().trim());
		for(int tt = 0; tt<t; tt++){
			int size = Integer.parseInt(in.readLine().trim());
			m = new int[size][size];
			int inter = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < inter; i++) {
				int[] d = readInts(in.readLine());
				for (int r = d[0]-1; r < d[2]; r++) 
					for (int c = d[1]-1; c < d[3]; c++) 
						m[r][c] = 1;
			}
			area = 0;
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					clearDP(r, c, size);
					for (int i = r + 1; i < size; i++) {
						for (int j = c + 1; j < size; j++) {
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
