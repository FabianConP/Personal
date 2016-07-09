package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10010_Wheres_Waldorf {
	static int[][] dir = { { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static char[][] a;
	static int n, m;
	static Point ans;

	static boolean check(int r, int c) {
		return r >= 0 && r < m && c >= 0 && c < n;
	}

	static void solve(String word) {
		int l = word.length() - 1;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				for (int[] d : dir)
					if (check(i + l * d[0], j + l * d[1])) {
						int r = i, c = j, index = 0;
						boolean ok = true;
						while (index <= l && ok) {
							if (word.charAt(index++) != a[r][c])
								ok = false;
							r += d[0];
							c += d[1];
						}
						if(ok && (i < ans.x  || (i == ans.x && j < ans.y))){
							ans.x = i;
							ans.y = j;
						}
					}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			in.readLine();
			int[] mn = readInts(in.readLine());
			m = mn[0];
			n = mn[1];
			a = new char[m][n];
			if (nCase != 0)
				out.append('\n');
			for (int i = 0; i < m; i++) {
				char[] row = in.readLine().trim().toCharArray();
				for (int j = 0; j < n; j++)
					a[i][j] = Character.toLowerCase(row[j]);
			}
			int queries = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < queries; i++) {
				String word = in.readLine().trim().toLowerCase();
				ans = new Point(m, n);
				solve(word);
				out.append(++ans.x + " " + ++ans.y + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split("\\s+");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
