package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _572_Oil_Deposits {

	public static char[][] map;
	public static int n, m;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
			{ -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void floodFill(int r, int c) {
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < n && c + dir[i][1] >= 0
					&& c + dir[i][1] < m
					&& map[r + dir[i][0]][c + dir[i][1]] == '@') {
				map[r + dir[i][0]][c + dir[i][1]] = '*';
				floodFill(r + dir[i][0], c + dir[i][1]);
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nm = readInts(line);
			n = nm[0];
			m = nm[1];
			if (n == 0 && m == 0)
				break;
			map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = in.readLine().trim().toCharArray();
			int deposits = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					if (map[i][j] == '@') {
						deposits++;
						map[i][j] = '*';
						floodFill(i, j);
					}
			out.append(deposits + "\n");
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
