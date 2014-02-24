package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10911_Forming_Quiz_Teams {

	public static double dist[][], min;

	public static double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	public static void DFS(int r, int used, int mask, double curD) {
		if (used >= dist.length || curD > min) {
			min = Math.min(curD, min);
			return;
		} else
			for (int i = r; i < dist.length; i++)
				if ((mask & (1 << i)) == 0) {
					mask |= (1 << i);
					for (int j = i + 1; j < dist.length; j++)
						if ((mask & (1 << j)) == 0)
							DFS(i + 1, used + 2, mask | (1 << j), curD
									+ dist[i][j]);
					mask ^= (1 << i);
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line) * 2;
			if (size == 0)
				break;
			dist = new double[size][size];
			Point[] stud = new Point[size];
			String[] data;
			for (int i = 0; i < stud.length; i++) {
				data = in.readLine().trim().split(" ");
				stud[i] = new Point(Integer.parseInt(data[1]),
						Integer.parseInt(data[2]));
			}
			for (int i = 0; i < dist.length; i++)
				for (int j = i + 1; j < dist.length; j++)
					dist[i][j] = dist[j][i] = distance(stud[i], stud[j]);
			min = Double.MAX_VALUE;
			DFS(0, 0, 0, 0);
			out.append("Case " + times++ + ": "
					+ String.format("%.2f\n", min).replace(",", "."));
		}
		System.out.print(out);
	}
}
