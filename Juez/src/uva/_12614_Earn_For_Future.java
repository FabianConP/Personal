package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12614_Earn_For_Future {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int cases = Integer.parseInt(line);
			for (int i = 0; i < cases; i++) {
				in.readLine();
				int[] a = readInts(in.readLine());
				int max = a[0];
				for (int e : a)
					max = Math.max(e, max);
				out.append("Case " + (i + 1) + ": " + max + "\n");
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
