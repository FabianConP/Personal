package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10036_Divisibility {

	public static int k, v[];
	public static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		dp = new boolean[10000][100];
		while ((line = in.readLine()) != null && line.length() != 0) {
			int t = Integer.parseInt(line.trim());
			for (int i = 0; i < t; i++) {
				int[] nk = readInts(in.readLine());
				cleanDP(nk[0], k = nk[1]);
				v = readInts(in.readLine(), k);
				dp[0][sum(0, v[0])] = true;
				for (int r = 1; r < v.length; r++) 
					for (int j = 0; j < k; j++) 
						if (dp[r - 1][j]){
							dp[r][sum(j, v[r])] = true;
							dp[r][sum(j, -v[r])] = true;
						}
				out.append((dp[nk[0]-1][0] ? "Divisible" : "Not divisible")+ "\n");
			}
		}
		System.out.print(out);
	}

	public static void cleanDP(int n, int k) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < k; j++)
				dp[i][j] = false;
	}

	public static int sum(int a, int b) {
		return ((a + b) % k) + ((a + b) % k >= 0 ? 0 : k);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
	
	public static int[] readInts(String line, int mod) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]) % mod;
		return a;
	}
}
