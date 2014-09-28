package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _722_Lakes {

	public static char[][] map;
	public static boolean[][] used;
	public static int n, m;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static int floodFill(int r, int c) {
		int area = 1;
		for (int i = 0; i < dir.length; i++)
			if (r + dir[i][0] >= 0 && r + dir[i][0] < n && c + dir[i][1] >= 0
					&& c + dir[i][1] < m && !used[r + dir[i][0]][c + dir[i][1]]
					&& map[r + dir[i][0]][c + dir[i][1]] == '0') {
				used[r + dir[i][0]][c + dir[i][1]] = true;
				area += floodFill(r + dir[i][0], c + dir[i][1]);
			}
		return area;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				if (i == 0)
					in.readLine();
				else
					out.append("\n");
				int[] t = readInts(in.readLine());
				ArrayList<String> l = new ArrayList<String>(100);
				while ((line = in.readLine()) != null
						&& line.matches(".*[10].*"))
					l.add(line.trim());
				n = l.size();
				m = l.get(0).length();
				map = new char[n][m];
				for (int j = 0; j < l.size(); j++)
					map[j] = l.get(j).toCharArray();
				if (map[t[0] - 1][t[1] - 1] == '1')
					out.append("0\n");
				else {
					used = new boolean[n][m];
					used[t[0] - 1][t[1] - 1] = true;
					out.append(floodFill(t[0] - 1, t[1] - 1) + "\n");
				}
			}
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
