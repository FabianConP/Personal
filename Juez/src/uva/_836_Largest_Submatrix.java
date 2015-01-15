package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _836_Largest_Submatrix {

	static int dp[][], area;
	static char[][] m;

	public static void clearDP(int r, int c, int size) {
		dp[r][c] = m[r][c] - '0';
		for (int i = r + 1; i < size; i++){
			dp[i][c] = dp[i - 1][c] + m[i][c] - '0';
			if(i-r+1==dp[i][c])
				area = Math.max(area, dp[i][c]);
		}
		for (int j = c + 1; j < size; j++){
			dp[r][j] = dp[r][j - 1] + m[r][j] - '0';
			if(j-c+1==dp[r][j])
				area = Math.max(area, dp[r][j]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		dp = new int[25][25];
		int times = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < times; t++) {
			in.readLine();
			String firstLine = in.readLine().trim();
			int size = firstLine.length();
			m = new char[size][size];
			m[0] = firstLine.toCharArray();
			for (int j = 1; j < size; j++)
				m[j] = in.readLine().trim().toCharArray();
			area = 0;
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					area = Math.max(area, m[r][c]-'0');
					clearDP(r, c, size);
					for (int i = r + 1; i < size; i++) {
						for (int j = c + 1; j < size; j++) {
							dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + -dp[i - 1][j - 1] + m[i][j] - '0';
							if ((i-r + 1) * (j-c + 1) == dp[i][j])
								area = Math.max(area, dp[i][j]);
						}
					}
				}
			}
			if(t!=0)
				out.append("\n");
			out.append(area + "\n");
		}
		System.out.print(out);
	}
}
