package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1641_ASCII_Area {

	public static int[][] map;
	public static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 },
			{ -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void flood(int r, int c) {
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < map.length
					&& c + dir[i][1] >= 0 && c + dir[i][1] < map[0].length
					&& map[r + dir[i][0]][c + dir[i][1]] == 0)
				if (i < 4) {
					map[r + dir[i][0]][c + dir[i][1]] = 3;
					flood(r + dir[i][0], c + dir[i][1]);

				} else {
					boolean diag = false;
					switch (i) {
					case 4:
						if (map[r + dir[2][0]][c + dir[2][1]] != 1
								&& map[r + dir[0][0]][c + dir[0][1]] != 1)
							diag = true;
						break;
					case 5:
						if (map[r + dir[3][0]][c + dir[3][1]] != 2
								&& map[r + dir[0][0]][c + dir[0][1]] != 2)
							diag = true;
						break;
					case 6:
						if (map[r + dir[2][0]][c + dir[2][1]] != 2
								&& map[r + dir[1][0]][c + dir[1][1]] != 2)
							diag = true;
						break;
					case 7:
						if (map[r + dir[3][0]][c + dir[3][1]] != 1
								&& map[r + dir[1][0]][c + dir[1][1]] != 1)
							diag = true;
						break;
					}
					if (diag) {
						map[r + dir[i][0]][c + dir[i][1]] = 3;
						flood(r + dir[i][0], c + dir[i][1]);
					}
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] hw = readInts(line);
			map = new int[hw[0] + 2][hw[1] + 2];
			char[] row;
			for (int i = 1; i < map.length - 1; i++) {
				row = in.readLine().trim().toCharArray();
				for (int j = 0; j < row.length; j++)
					map[i][j + 1] = row[j] == '.' ? 0 : row[j] == '/' ? 1 : 2;
			}
			flood(0, 0);
			int area = 0;
			for (int r = 1; r < map.length - 1; r++) 
				for (int c = 1; c < map[r].length - 1; c++) 
					if (map[r][c] == 0)
						area += 2;
					else if (map[r][c] <= 2)
						area++;
			area /= 2;
			out.append(area + "\n");
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
