package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11854_Egypt {

	static int[][] p = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 },
			{ 2, 0, 1 }, { 2, 1, 0 } };

	static int s(int a) {
		return a * a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line.trim());
			if (v[0] == 0 && v[1] == 0 && v[2] == 0)
				break;
			boolean right = false;
			for (int i = 0; i < p.length && !right; i++)
				right |= s(v[p[i][0]]) == s(v[p[i][1]]) + s(v[p[i][2]]);
			if (right)
				out.append("right\n");
			else
				out.append("wrong\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}

}
