package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _927_Integer_Sequences_from_Addition_of_Terms {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int[] coef = readInts(in.readLine());
				long n = 1, next = n, k, d, ans = 0;
				d = Integer.parseInt(in.readLine().trim());
				k = Integer.parseInt(in.readLine().trim());
				while (next + d * n <= k)
					next += d * n++;
				for (int j = 1; j < coef.length; j++)
					ans += coef[j] * Math.pow(n, j - 1);
				out.append(ans + "\n");
			}

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
