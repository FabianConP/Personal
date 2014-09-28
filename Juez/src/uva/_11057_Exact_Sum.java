package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class _11057_Exact_Sum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		BitSet prices = new BitSet(1000002);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			int[] p = readInts(in.readLine());
			int sum = Integer.parseInt(in.readLine().trim());
			prices.clear();
			int dif = Integer.MAX_VALUE, a = dif, b = -a;
			for (int i = 0; i < p.length; i++) {
				if (p[i] <= sum && prices.get(sum - p[i])
						&& Math.abs(sum - 2 * p[i]) < dif) {
					a = Math.min(p[i], sum-p[i]);
					b = Math.max(p[i], sum-p[i]);
					dif = Math.abs(sum - 2 * p[i]);
				}
				prices.set(p[i], true);
			}
			out.append("Peter should buy books whose prices are " + a + " and "
					+ b + ".\n\n");
			in.readLine();
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
