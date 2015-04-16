package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class _196_Spreadsheet {

	static final int MAX_ROWS = 1000;
	static final int MAX_COLS = 18279;
	static long m[][] = new long[MAX_ROWS][MAX_COLS], r, c;
	static HashMap<Point, String> f;

	public static long solve(int i, int j) {
		Point d = new Point(i, j);
		if (!f.containsKey(d))
			return m[i][j];
		String[] c = f.remove(d).substring(1).split("\\+");
		long sum = 0;
		for (int k = 0; k < c.length; k++) {
			d = getPoint(c[k]);
			sum += solve(d.x, d.y);
		}
		return m[i][j] = sum;
	}

	public static Point getPoint(String data) {
		Point d = new Point(0, 0);
		int lim = 0, col = 1;
		while (Character.isAlphabetic(data.charAt(lim)))
			lim++;
		for (int i = lim - 1; i >= 0; i--, col*=26)
			d.y += (data.charAt(i) - 'A'+1)*col;
		d.x = Integer.parseInt(data.substring(lim)) - 1;
		d.y--;
		return d;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int n = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < n; i++) {
			int[] rc = readInts(in.readLine());
			c = rc[0];
			r = rc[1];
			f = new HashMap<Point, String>();
			for (int j = 0; j < r; j++) {
				String[] row = in.readLine().trim().split(" ");
				for (int k = 0; k < row.length; k++)
					if (row[k].startsWith("="))
						f.put(new Point(j, k), row[k]);
					else
						m[j][k] = Integer.parseInt(row[k]);
			}
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (k != 0)
						out.append(" ");
					out.append(solve(j, k));
				}
				out.append("\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
