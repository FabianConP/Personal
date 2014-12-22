package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2294_Hangover {

	public static double[] dp = new double[280];

	public static int lower_bound(double key) {
		int lower = 0, upper = dp.length - 1, ans = -1;
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (key < dp[mid])
				upper = mid - 1;
			else {
				lower = mid + 1;
				ans = mid;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		for (int i = 1; i < dp.length; i++)
			dp[i] = (1.0 / (i + 1)) + dp[i - 1];
		while ((line = in.readLine()) != null && line.length() != 0) {
			double l = Double.parseDouble(line.trim());
			if (l == 0)
				break;
			out.append((lower_bound(l) + 1) + " card(s)\n");
		}
		System.out.print(out);
	}
}
