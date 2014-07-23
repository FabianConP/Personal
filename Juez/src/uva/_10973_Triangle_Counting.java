package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.HashSet;

public class _10973_Triangle_Counting {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int[] nm = readInts(in.readLine());
				int n = nm[0], m = nm[1];
				boolean[][] map = new boolean[n + 1][n + 1];
				for (int j = 0; j < m; j++) {
					int[] ab = readInts(in.readLine());
					map[ab[0]][ab[1]] = true;
					map[ab[1]][ab[0]] = true;
				}
				HashSet<Three> set = new HashSet<Three>();
				Three t;
				for (int r = 1; r < map.length; r++)
					for (int c = r + 1; c < map.length; c++)
						if (map[r][c])
							for (int k = c + 1; k < map.length; k++)
								if (map[c][k] && map[k][r]) {
									t = new Three(r, c, k);
									if (!set.contains(t))
										set.add(t);
								}
				out.append(set.size() + "\n");
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

	private static class Three {
		int a, b, c;

		public Three(int a, int b, int c) {
			int[] toSort = new int[] { a, b, c };
			Arrays.sort(toSort);
			this.a = toSort[0];
			this.b = toSort[1];
			this.c = toSort[2];
		}

		@Override
		public boolean equals(Object arg0) {
			Three o = (Three) arg0;
			return this.a == o.a && this.b == o.b && this.c == o.c;
		}

		@Override
		public int hashCode() {
			String str = a + " " + b + " " + c;
			return str.hashCode();
		}

	}
}
