package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _703_Triple_Ties_The_Organizer_s_Nightmare {

	public static boolean[][][] taken;
	public static int[][] m;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			m = new int[size][size];
			taken = new boolean[size][size][size];
			for (int i = 0; i < m.length; i++)
				m[i] = readInts(in.readLine());
			ArrayList<Draw> list = new ArrayList<Draw>();
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m[i].length; j++)
					if (i != j)
						for (int k = 0; k < m[j].length; k++)
							if (i != k && j != k)
								if (!taken[i][j][k] && noVictory(i, j, k)) {
									mark(i, j, k);
									list.add(new Draw(i, j, k, false));
								} else if ((m[i][j] + m[j][k] + m[k][i]) == 3
										&& !taken[i][j][k]) {
									mark(i, j, k);
									list.add(new Draw(i, j, k, true));
								}
			Collections.sort(list);
			out.append(list.size() + "\n");
			for (int i = 0; i < list.size(); i++)
				out.append(list.get(i) + "\n");
		}
		System.out.print(out);
	}

	public static boolean noVictory(int i, int j, int k) {
		return (m[i][j] + m[i][k] + m[j][i] + m[j][k] + m[k][i] + m[k][j]) == 0;
	}

	public static void mark(int i, int j, int k) {
		taken[i][j][k] = taken[i][k][j] = true;
		taken[j][i][k] = taken[j][k][i] = true;
		taken[k][i][j] = taken[k][j][i] = true;
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

	static class Draw implements Comparable<Draw> {
		int a, b, c;
		boolean v;

		public Draw(int a, int b, int c, boolean v) {
			int[] p = new int[] { a + 1, b + 1, c + 1 };
			if (v) {
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
			} else {
				Arrays.sort(p);
				this.a = p[0];
				this.b = p[1];
				this.c = p[2];
			}
		}

		@Override
		public int compareTo(Draw arg0) {
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
