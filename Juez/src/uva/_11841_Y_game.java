package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11841_Y_game {

	static final int L = 1, R = 2, D = 4, ALL = L + R + D;
	static final int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	static int n;

	static boolean[][] on;
	static int[][] v;

	static boolean inside(int r, int c) {
		return r >= 0 && r <= n && c >= 0 && c <= n - r;
	}

	static int side(int r, int c) {
		int x = c, y = r, z = n - x - y;
		int s = (x == 0) ? L : 0;
		s |= (y == 0) ? R : 0;
		s |= (z == 0) ? D : 0;
		return s;
	}

	static int DFS(int r, int c, int t) {
		int s = side(r, c);
		for (int[] d : dir)
			if (inside(r + d[0], c + d[1]) && v[r + d[0]][c + d[1]] != t && on[r + d[0]][c + d[1]]) {
				v[r + d[0]][c + d[1]] = t;
				s |= DFS(r + d[0], c + d[1], t);
			}
		return s;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			int[] nm = readInts(line);
			n = nm[0];
			int bennies = nm[1];
			if (n == 0 && bennies == 0)
				break;
			if (nCase == 19)
				nCase = nCase + 0;
			nCase++;
			on = new boolean[n + 1][n + 1];
			v = new int[n + 1][n + 1];
			for (int i = 0; i < bennies; i++) {
				int[] xyz = readInts(in.readLine());
				int x = xyz[0], y = xyz[1], z = xyz[2];
				on[y][x] = true;
			}
			boolean benny = false;
			for (int i = 0; i <= n && !benny; i++)
				if (on[i][0] && DFS(i, 0, i + 1) == ALL)
					benny = true;

			out.append(benny ? "Benny\n" : "Willy\n");
		}
		System.out.print(out);
	}

	static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
