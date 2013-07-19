package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Locale;

public class _147_Dollars {

	public static long[] dp2;// Array of dif. ways
	public static int[] coins = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000,
			5000, 10000 };

	public static void diffWays(int n) {
		dp2 = new long[n + 1];
		dp2[0] = 1;
		for (int i = 0; i < coins.length; i++)
			for (int j = coins[i]; j <= n; j++)
				dp2[j] += dp2[j - coins[i]];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		diffWays(30002);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			double amount = Double.parseDouble(line);
			int ra = (int) ((amount * 100)+0.5);
			if (amount == 0)
				break;
			System.out.printf(Locale.US, "%6.2f", amount);
			System.out.printf(Locale.US, "%17d\n", dp2[ra]);
		} while (line != null && line.length() != 0);
	}

}
