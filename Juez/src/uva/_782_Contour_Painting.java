package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _782_Contour_Painting {

	public static char[][] map;
	public static int n, m;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void floodFill(int r, int c) {
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < n + 2
					&& c + dir[i][1] >= 0 && c + dir[i][1] < m + 2
					&& map[r + dir[i][0]][c + dir[i][1]] != 'X'
					&& map[r + dir[i][0]][c + dir[i][1]] != '#') {
				map[r + dir[i][0]][c + dir[i][1]] = '#';
				floodFill(r + dir[i][0], c + dir[i][1]);
			}
	}

	public static boolean around(int r, int c) {
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < n + 2
					&& c + dir[i][1] >= 0 && c + dir[i][1] < m + 2
					&& map[r + dir[i][0]][c + dir[i][1]] == 'X')
				return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "", endInput;
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				ArrayList<String> l = new ArrayList<String>(40);
				int maxCols = 0;
				char[] row;
				while ((line = in.readLine()) != null && !line.startsWith("_")) {
					l.add(line);
					maxCols = Math.max(maxCols, line.length());
				}
				endInput = line;
				n = l.size();
				m = maxCols;
				map = new char[n + 2][m + 2];
				for (int j = 0; j < map.length; j++)
					Arrays.fill(map[j], ' ');
				for (int j = 0; j < n; j++) {
					row = l.get(j).toCharArray();
					for (int k = 0; k < row.length; k++)
						map[j + 1][k + 1] = row[k];

				}
				for (int r = 1; r <= n; r++)
					for (int c = 1; c <= m; c++)
						if (map[r][c] == '*') {
							map[r][c] = '#';
							floodFill(r, c);
						}

				for (int r = 0; r <= n + 1; r++)
					for (int c = 0; c <= m + 1; c++)
						if (map[r][c] == '#') {
							if (around(r, c))
								map[r][c] = '#';
							else
								map[r][c] = ' ';
						}
				int iniR = 1, iniC = 1;
				int endR = n + 1, endC = m + 1;
				for (int r = 0; r <= n + 1; r++)
					for (int c = 0; c <= m + 1; c++) {
						iniR = (r == 0 && map[r][c] == '#') ? 0 : Math.min(iniR,1);
						iniC = (c == 0 && map[r][c] == '#') ? 0 : Math.min(iniC,1);
						endR = (r == n + 1 && map[r][c] == '#') ? n+2 : Math.max(endR, 1);
						endC = (c == m + 1 && map[r][c] == '#') ? m+2 : Math.max(endC, 1);
					}
				line = "";
				for (int r = iniR; r < endR; r++, line = "") {
					for (int c = iniC; c < endC; c++)
						line += map[r][c];
					out.append((line.replaceAll("\\s+$", "") + "\n"));
				}
				out.append(endInput + "\n");
			}

		}
		System.out.print(out);
	}
}
