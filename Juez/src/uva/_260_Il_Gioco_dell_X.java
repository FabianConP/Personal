package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _260_Il_Gioco_dell_X {

	public static char[][] map;
	public static boolean used[][], winWhite;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
			{ -1, -1 }, { 1, 1 } };
	public static int size;

	public static void floodFill(int r, int c) {
		if (winWhite)
			return;
		if (c == size - 1) {
			winWhite = true;
			return;
		} else
			for (int i = 0; i < dir.length; i++)
				if (r + dir[i][0] >= 0 && r + dir[i][0] < size
						&& c + dir[i][1] >= 0 && c + dir[i][1] < size
						&& !used[r + dir[i][0]][c + dir[i][1]]
						&& map[r + dir[i][0]][c + dir[i][1]] == 'w') {
					used[r + dir[i][0]][c + dir[i][1]] = true;
					floodFill(r + dir[i][0], c + dir[i][1]);
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			size = Integer.parseInt(line.trim());
			if (size == 0)
				break;
			map = new char[size][size];
			used = new boolean[size][size];
			for (int i = 0; i < size; i++)
				map[i] = in.readLine().trim().toCharArray();
			winWhite = false;
			for (int i = 0; i < size && !winWhite; i++)
				if (map[i][0] == 'w') 
					floodFill(i, 0);
			out.append(nCase++ + " ");
			out.append((winWhite ? "W" : "B") + "\n");
		}
		System.out.print(out);
	}
}
