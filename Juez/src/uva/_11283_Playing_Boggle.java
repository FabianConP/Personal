package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11283_Playing_Boggle {

	static int[][] dir = { { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static char[][] a;
	static boolean u[][], founded;
	static String word;
	static int score;

	static boolean check(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}

	static int score(int l) {
		if (l <= 4)
			return 1;
		else if (l <= 5)
			return 2;
		else if (l <= 6)
			return 3;
		else if (l <= 7)
			return 5;
		return 11;
	}

	static void solve() {
		for (int i = 0; i < 4; i++) 
			for (int j = 0; j < 4; j++) 
				if (a[i][j] == word.charAt(0)) {
					u[i][j] = true;
					solveBack(a[i][j] + "", i, j);
					u[i][j] = false;
				}
	}

	static void solveBack(String s, int r, int c) {
		if (founded)
			return;
		if (s.length() == word.length()) {
			if (!founded) {
				founded = true;
				score += score(word.length());
			}
		} else
			for (int[] d : dir) 
				if (check(r + d[0], c + d[1]) && !u[r + d[0]][c + d[1]]
						&& word.charAt(s.length()) == a[r + d[0]][c + d[1]]) {
					u[r + d[0]][c + d[1]] = true;
					solveBack(s + a[r + d[0]][c + d[1]], r + d[0], c + d[1]);
					u[r + d[0]][c + d[1]] = false;
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 1; nCase <= nCases; nCase++) {
			in.readLine();
			a = new char[4][];
			for (int i = 0; i < 4; i++)
				a[i] = in.readLine().trim().toCharArray();
			score = 0;
			int queries = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < queries; i++) {
				founded = false;
				u = new boolean[4][4];
				word = in.readLine().trim();
				solve();
			}
			out.append("Score for Boggle game #" + nCase + ": " + score + "\n");
		}
		System.out.print(out);
	}

}
