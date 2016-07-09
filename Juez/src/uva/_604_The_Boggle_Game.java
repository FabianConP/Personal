package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class _604_The_Boggle_Game {

	static int[][] dir = { { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static char[][] a, b;
	static boolean[][] ua, ub;
	static ArrayList<String> ans;
	static HashSet<String> wordsa, wordsb;

	static boolean isVowel(char l) {
		return l == 'A' || l == 'E' || l == 'I' || l == 'O' || l == 'U' || l == 'Y';
	}

	static void solve() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				solveBack("", i, j, 0, 0, 0);
	}

	static void solveBack(String s, int r, int c, int cnt, int vow, int con) {
		if (cnt == 4) {
			if (!wordsa.contains(s)) {
				wordsa.add(s);
				if (wordsb.contains(s))
					ans.add(s);
			}
		} else
			for (int[] d : dir)
				if (r + d[0] >= 0 && r + d[0] < 4 && c + d[1] >= 0 && c + d[1] < 4 && !ua[r + d[0]][c + d[1]]) {
					ua[r + d[0]][c + d[1]] = true;
					if (isVowel(a[r + d[0]][c + d[1]]) && vow + 1 <= 2)
						solveBack(s + a[r + d[0]][c + d[1]], r + d[0], c + d[1], cnt + 1, vow + 1, con);
					else if (!isVowel(a[r + d[0]][c + d[1]]) && con + 1 <= 2)
						solveBack(s + a[r + d[0]][c + d[1]], r + d[0], c + d[1], cnt + 1, vow, con + 1);
					ua[r + d[0]][c + d[1]] = false;
				}
	}

	static void getWordsB() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				getWordsBBack("", i, j, 0, 0, 0);
	}

	static void getWordsBBack(String s, int r, int c, int cnt, int vow, int con) {
		if (cnt == 4)
			wordsb.add(s);
		else
			for (int[] d : dir)
				if (r + d[0] >= 0 && r + d[0] < 4 && c + d[1] >= 0 && c + d[1] < 4 && !ub[r + d[0]][c + d[1]]) {
					ub[r + d[0]][c + d[1]] = true;
					if (isVowel(b[r + d[0]][c + d[1]]) && vow + 1 <= 2)
						getWordsBBack(s + b[r + d[0]][c + d[1]], r + d[0], c + d[1], cnt + 1, vow + 1, con);
					else if (!isVowel(b[r + d[0]][c + d[1]]) && con + 1 <= 2)
						getWordsBBack(s + b[r + d[0]][c + d[1]], r + d[0], c + d[1], cnt + 1, vow, con + 1);
					ub[r + d[0]][c + d[1]] = false;
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		a = new char[4][4];
		b = new char[4][4];
		ua = new boolean[4][4];
		ub = new boolean[4][4];
		int nCase = 0;
		while ((line = in.readLine()) != null) {
			if (line.equals("#"))
				break;
			if (nCase++ != 0)
				out.append('\n');
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					ua[i][j] = ub[i][j] = false;
			ans = new ArrayList<>();
			wordsa = new HashSet<>();
			wordsb = new HashSet<>();
			String[] row = line.trim().split("\\s+");
			for (int i = 0; i < 4; i++)
				a[0][i] = row[i].charAt(0);
			for (int i = 4; i < 8; i++)
				b[0][i - 4] = row[i].charAt(0);
			for (int j = 1; j < 4; j++) {
				row = in.readLine().trim().split("\\s+");
				for (int i = 0; i < 4; i++)
					a[j][i] = row[i].charAt(0);
				for (int i = 4; i < 8; i++)
					b[j][i - 4] = row[i].charAt(0);
			}
			getWordsB();
			solve();
			if (ans.isEmpty())
				out.append("There are no common words for this pair of boggle boards.\n");
			else {
				Collections.sort(ans);
				for (int i = 0; i < ans.size(); i++)
					out.append(ans.get(i) + "\n");
			}
			in.readLine();
		}
		System.out.print(out);
	}

}
