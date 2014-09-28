package uva;
import java.io.IOException;
import java.util.Scanner;

public class _11137_Ingenuous_Cubrency {

	public static long[] dp2;// Array of dif. ways
	public static int[] coins;

	public static void diffWays(int n) {
		dp2 = new long[n + 1];
		dp2[0] = 1;
		for (int i = 0; i < coins.length; i++)
			for (int j = coins[i]; j <= n; j++)
				dp2[j] += dp2[j - coins[i]];
	}

	public static void fillCoins() {
		coins = new int[21];
		for (int i = 1; i <= 21; i++)
			coins[i - 1] = (int) Math.pow(i, 3);
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		fillCoins();
		diffWays(10001);
		StringBuilder out = new StringBuilder();
		while (scan.hasNext()) {
			int n = scan.nextInt();
			out.append(dp2[n]).append("\n");
		}
		System.out.print(out);
	}
}
