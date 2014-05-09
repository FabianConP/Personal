package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class _562_Dividing_coins {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int times = in.nextInt();
		for (int i = 0; i < times; i++) {
			int nCoins = in.nextInt();
			int[] c = new int[nCoins];
			for (int j = 0; j < c.length; j++)
				c[j] = in.nextInt();
			Arrays.sort(c);
			int sum = 0, ans = 0;
			for (int j = 0; j < c.length; j++)
				sum += c[j];
			ans = sum;
			boolean[][] cr = new boolean[nCoins][sum + 1];
			for (int j = 0; j < c.length; j++) {
				ans = Math.min(Math.abs(c[j] * 2 - sum), ans);
				cr[j][0] = true;
				if (j == 0)
					cr[j][c[j]] = true;
				else
					for (int k = 0; k <= sum; k++) {
						cr[j][k] |= cr[j - 1][k];
						if (k >= c[j] && cr[j - 1][k - c[j]]) {
							ans = Math.min(Math.abs(k * 2 - sum), ans);
							cr[j][k] = true;
						}
					}
			}
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
