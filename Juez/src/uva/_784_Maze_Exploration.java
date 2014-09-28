package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _784_Maze_Exploration {

	public static char[][] map;
	public static int n, m;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void floodFill(int r, int c) {
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < n && c + dir[i][1] >= 0
					&& c + dir[i][1] < m
					&& map[r + dir[i][0]][c + dir[i][1]] == ' ') {
				map[r + dir[i][0]][c + dir[i][1]] = '#';
				floodFill(r + dir[i][0], c + dir[i][1]);
			}
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
				map = new char[n][m];
				for (int j = 0; j < map.length; j++)
					Arrays.fill(map[j], ' ');
				for (int j = 0; j < n; j++) {
					row = l.get(j).toCharArray();
					for (int k = 0; k < row.length; k++)
						map[j][k] = row[k];
				}
				for (int r = 0; r < n; r++)
					for (int c = 0; c < m; c++)
						if (map[r][c] == '*') {
							map[r][c] = '#';
							floodFill(r, c);
						}
				line = "";
				for (int r = 0; r < n; r++, line = "") {
					for (int c = 0; c < m; c++)
						line += map[r][c];
					out.append((line.replaceAll("\\s+$", "") + "\n"));
				}
				out.append(endInput + "\n");
			}

		}
		System.out.print(out);
	}
}
