package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _10465_Homer_Simpson {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] mnt = readInts(line);
			int m = mnt[0], n = mnt[1], t = mnt[2];
			int[] dp = new int[t + 1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			for (int i = m; i < dp.length; i++)
				if (dp[i - m] != -1)
					dp[i] =dp[i - m] + 1;
			for (int i = n; i < dp.length; i++)
				if (dp[i - n] != -1)
					dp[i] = Math.max(dp[i], dp[i - n] + 1);
			int beer = t;
			while (beer >= 0 && dp[beer] == -1)
				beer--;
			out.append(dp[beer]);
			if (beer != t)
				out.append(" " + (t - beer) + "\n");
			else
				out.append("\n");

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
