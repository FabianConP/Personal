package pku;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2499_Binary_Tree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			int ab[], r = 0, l = 0, div = 0;
			for (int i = 0; i < times; i++, r = 0, l = 0) {
				ab = readInts(in.readLine());
				while ((ab[0] > 1 || ab[1] > 1) && ab[0] != 0 && ab[1] != 0) {
					if (ab[0] > ab[1]) {
						div = ab[0] / ab[1];
						ab[0] -= ab[1] * div;
						l += div;
					} else {
						div = ab[1] / ab[0];
						ab[1] -= ab[0] * div;
						r += div;
					}
				}
				if (ab[0] == 0)
					l--;
				if (ab[1] == 0)
					r--;
				out.append("Scenario #" + (i + 1) + ":\n" + l + " " + r
						+ "\n\n");
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
