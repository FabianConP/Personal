package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _102_Ecological_Bin_Packing {

	public static final long MOD = 1000000007;
	public static final String[] bins = { "B", "G", "C" };

	public static String build(int a, int b, int c) {
		return bins[a] + bins[b] + bins[c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int v[] = readInts(line), con = 0;
			int[][] m = new int[3][3];
			for (int i = 0; i < v.length; i++)
				m[con / 3][con % 3] = v[con++];
			int curMoves = 0, minMoves = Integer.MAX_VALUE;
			int[] p = { 0, 0, 0 }, ans;
			String name = "ZZZ", curName = "";
			for (p[0] = 0; p[0] < m.length; p[0]++) {
				for (p[1] = 0; p[1] < m.length; p[1]++) {
					if (p[0] != p[1])
						for (p[2] = 0; p[2] < m.length; p[2]++) {
							curMoves = 0;
							if (p[0] != p[2] && p[1] != p[2]) {
								for (int i = 0; i < m.length; i++) {
									for (int j = 0; j < m.length; j++) {
										if (j != p[i]) {
											curMoves += m[i][j];
										}
									}
								}
								// System.out.println("Name: " + build(p[0],
								// p[1], p[2]) + ", "+ curMoves);
								if (curMoves <= minMoves) {
									curName = build(p[0], p[1], p[2]);
									if (curMoves < minMoves) {
										minMoves = curMoves;
										name = curName;
									} else if (name.compareTo(curName) > 0)
										name = curName;
								}
							}
						}
				}
			}
			out.append(name + " " + minMoves + "\n");

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
