package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11059_Maximum_Product {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			long[] a = readLongs(in.readLine());
			long ans = 0, cur = 1;
			for (int i = 0; i < a.length; i++) {
				cur = 1;
				for (int j = i; j < a.length; j++) {
					cur *= a[j];
					ans = Math.max(cur, ans);
				}
			}
			out.append("Case #" + times++ + ": The maximum product is " + ans
					+ ".\n\n");
			in.readLine();
		}
		System.out.print(out);
	}

	public static long[] readLongs(String line) {
		String[] w = line.trim().split(" ");
		long[] a = new long[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Long.parseLong(w[i].trim());
		return a;
	}
}
