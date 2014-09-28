package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11094_Continents {
	public static char[][] m;
	public static char t;
	public static int curSize;

	public static void floodFill(int i, int j, char p, char f) {
		m[i][j] = f;
		curSize++;
		if (i + 1 < m.length && m[i + 1][j] != p && m[i + 1][j] == t)
			floodFill(i + 1, j, p, f);
		if (i - 1 >= 0 && m[i - 1][j] != p && m[i - 1][j] == t)
			floodFill(i - 1, j, p, f);
		if (m[i][(j + 1) % (m[0].length)] != p
				&& m[i][(j + 1) % (m[0].length)] == t)
			floodFill(i, (j + 1) % (m[0].length), p, f);
		if ((j - 1 >= 0 && m[i][j - 1] != p && m[i][j - 1] == t)
				|| (j - 1 < 0 && m[i][m[0].length - 1] != p && m[i][m[0].length - 1] == t))
			floodFill(i, (j - 1) < 0 ? m[0].length - 1 : j - 1, p, f);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] MN = readInts(line);
			m = new char[MN[0]][MN[1]];
			char[] curLine;
			for (int i = 0; i < MN[0]; i++) {
				curLine = in.readLine().toCharArray();
				System.arraycopy(curLine, 0, m[i], 0, curLine.length);
			}
			int[] XY = readInts(in.readLine());
			t = m[XY[0]][XY[1]];
			floodFill(XY[0], XY[1], '*', '*');
			m[XY[0]][XY[1]] = '`';
			int max = 0;
			for (int i = 0; i < MN[0]; i++)
				for (int j = 0; j < MN[1]; j++)
					if (m[i][j] == t) {
						curSize = 0;
						floodFill(i, j, '*', '*');
						max = Math.max(max, curSize);
					}
			out.append(max + "\n");
			in.readLine();
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
