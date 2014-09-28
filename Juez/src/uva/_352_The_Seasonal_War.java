package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _352_The_Seasonal_War {

	public static char[][] map;
	public static int size;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
			{ -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void floodFill(int r, int c) {
		map[r][c] = '0';
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < size
					&& c + dir[i][1] >= 0 && c + dir[i][1] < size
					&& map[r + dir[i][0]][c + dir[i][1]] == '1')
				floodFill(r + dir[i][0], c + dir[i][1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			size = Integer.parseInt(line.trim());
			map = new char[size][size];
			for (int i = 0; i < size; i++)
				map[i] = in.readLine().trim().toCharArray();
			int eagles = 0;
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					if (map[i][j] == '1') {
						eagles++;
						floodFill(i, j);
					}
			out.append("Image number " + nCase++ + " contains " + eagles
					+ " war eagles.\n");
		}
		System.out.print(out);
	}
}
