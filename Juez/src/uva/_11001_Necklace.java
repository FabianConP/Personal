package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11001_Necklace {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] ab = readInts(line);
			int a = ab[0], b = ab[1];
			if (a == 0 && b == 0)
				break;
			double maxSize = Double.MIN_VALUE, size = 0;
			int ans = Integer.MAX_VALUE;
			for (int i = 1; i <= a; i++) {
				size = (a / (1.0 * i)) - b;
				if (size > 0) {
					size = 0.3 * Math.sqrt(size) * i;
					if (Math.abs(maxSize - size) <= 1e-12) {
						ans = Integer.MAX_VALUE;
						break;
					} else if (size > maxSize) {
						maxSize = size;
						ans = i;
					}
				}
			}
			if (ans == Integer.MAX_VALUE)
				out.append("0\n");
			else
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
