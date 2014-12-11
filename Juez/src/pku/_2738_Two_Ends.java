package pku;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2738_Two_Ends {

	public static final int INF = Integer.MAX_VALUE, MAX = 1000;
	public static int[] v, dp[];

	public static int dp(int s, int e, boolean t) {
		if (s > e)
			return 0;
		if (dp[s][e] != INF)
			return dp[s][e];
		int max = 0;
		if (!t) {
			if (v[s] < v[e])
				max = dp(s, e - 1, !t);
			else
				max = dp(s + 1, e, !t);
		} else
			max = Math.max(dp(s, e - 1, !t) + v[e], dp(s + 1, e, !t) + v[s]);
		return dp[s][e] = max;
	}
	
	public static void cleanDP(int n){
		for (int i = 0; i < n; i++) 
			for (int j = i; j < n; j++) 
				dp[i][j] = INF;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int game = 1;
		dp = new int[MAX][MAX]; 
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] data = readInts(line);
			if (data[0] == 0)
				break;
			v = new int[data.length - 1];
			cleanDP(v.length);
			int sumAll = 0;
			for (int i = 0; i < v.length; i++) {
				v[i] = data[i + 1];
				sumAll += v[i];
			}
			int greedyA = 0, s = 0, e = v.length - 1, turn = 0;
			while (s <= e) {
				int max = 0;
				if (v[s] < v[e])
					max = v[e--];
				else
					max = v[s++];
				greedyA += (turn++ % 2 == 0) ? max : 0;
			}
			int optimalA = dp(0, v.length - 1, true);
			int ans = Math.max(2*greedyA, 2*optimalA)-sumAll;
			out.append("In game "+game+++", the greedy strategy might lose by as many as "+ans+" points.\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
