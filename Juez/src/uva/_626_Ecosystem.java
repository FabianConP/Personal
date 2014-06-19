package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _626_Ecosystem {

	public static boolean[][][] taken;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			int[][] m = new int[size][size];
			for (int i = 0; i < m.length; i++)
				m[i] = readInts(in.readLine());
			taken = new boolean[101][101][101];
			ArrayList<Cycle> l = new ArrayList<Cycle>();
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m[i].length; j++)
					if (m[i][j] == 1)
						for (int k = 0; k < m[j].length; k++)
							if (m[j][k] == 1 && m[k][i] == 1 && i != j
									&& i != k && j != k && !taken[i][j][k]) {
								taken[i][j][k] = true;
								taken[j][k][i] = true;
								taken[k][i][j] = true;
								l.add(new Cycle(i, j, k));
							}
			Collections.sort(l);
			for (int i = 0; i < l.size(); i++)
				out.append(l.get(i) + "\n");
			out.append("total:" + l.size() + "\n\n");
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

	static class Cycle implements Comparable<Cycle> {
		int a, b, c;

		public Cycle(int a, int b, int c) {
			int[] p = new int[] { a + 1, b + 1, c + 1 };
			boolean asc, desc;
			for (int i = 0; i <= 2; i++) {
				asc = desc = true;
				for (int j = 0; j <= 1; j++)
					if (p[(i + j + 1) % 3] > p[(i + j) % 3])
						desc = false;
					else if (p[(i + j + 1) % 3] < p[(i + j) % 3])
						asc = false;
				if (asc || desc) {
					this.a = p[i];
					this.b = p[(i + 1) % 3];
					this.c = p[(i + 2) % 3];
					break;
				}
			}
		}

		@Override
		public int compareTo(Cycle arg0) {
			if (this.a == arg0.a)
				if (this.b == arg0.b)
					return this.c - arg0.c;
				else
					return this.b - arg0.b;
			return this.a - arg0.a;
		}

		@Override
		public String toString() {
			return a + " " + b + " " + c;
		}

	}
}
