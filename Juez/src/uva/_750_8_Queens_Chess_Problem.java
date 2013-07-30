package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _750_8_Queens_Chess_Problem {

	public static boolean[][] m;
	public static int nsol;
	public static ArrayList<String> solutions;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			int[] data;
			for (int i = 0; i < times; i++) {
				if (i != 0)
					out.append("\n");
				in.readLine();
				data = retInts(in.readLine());
				m = new boolean[9][9];
				m[data[0]][data[1]] = true;
				nsol = 1;
				solutions = new ArrayList<String>();
				queens(1, "");
				Collections.sort(solutions);
				out.append("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n\n");
				for (int j = 0; j < solutions.size(); j++)
					out.append(((j + 1 < 10) ? " " : "") + (j + 1) + "      "
							+ solutions.get(j) + "\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void queens(int c, String path) {
		if (c == 9) // Find solution
			solutions.add(path.trim());
		else if (checkColumn(c)) {
			for (int j = 1; j <= 8; j++)
				if (checkPos(c, j)) {
					m[j][c] = true;
					queens(c + 1, path + j + " ");
					m[j][c] = false;
				}
		} else
			for (int i = 1; i <= 8; i++)
				if (m[i][c])
					queens(c + 1, path + i + " ");
	}

	public static boolean checkPos(int c, int r) {
		for (int i = 1; i <= 8; i++)
			if (m[r][i] && i != c)
				return false;
		for (int i = c - 1, j = r - 1; i >= 1 && j >= 1; i--, j--)
			if (m[j][i])
				return false;
		for (int i = c + 1, j = r + 1; i <= 8 && j <= 8; i++, j++)
			if (m[j][i])
				return false;
		for (int i = c - 1, j = r + 1; i >= 1 && j <= 8; i--, j++)
			if (m[j][i])
				return false;
		for (int i = c + 1, j = r - 1; i <= 8 && j >= 1; i++, j--)
			if (m[j][i])
				return false;
		return true;
	}

	public static boolean checkColumn(int c) {
		for (int i = 1; i <= 8; i++)
			if (m[i][c])
				return false;
		return true;
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
