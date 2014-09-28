package uva;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10496_Collecting_Beepers {

	public static Point list[], start;
	public static boolean[] visited;
	public static int minPath;

	public static void back(int length, int cur, int last) {
		if (length == list.length - 1) {
			if (cur + dis(list[last], start) < minPath)
				minPath = cur + dis(list[last], start);
		} else
			for (int i = 1; i < list.length; i++)
				if (!visited[i]) {
					visited[i] = true;
					back(length + 1, cur + dis(list[last], list[i]), i);
					visited[i] = false;
				}
	}

	public static int dis(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int test = Integer.parseInt(line.trim()), v[];
			for (int i = 0; i < test; i++) {
				in.readLine();
				start = toPoint(retInts(in.readLine()));
				int items = Integer.parseInt(in.readLine().trim());
				list = new Point[items + 1];
				list[0] = start;
				visited = new boolean[items + 1];
				for (int j = 1; j <= items; j++)
					list[j] = toPoint(retInts(in.readLine()));
				minPath = Integer.MAX_VALUE;
				back(0, 0, 0);
				out.append("The shortest path has length " + minPath + "\n");
			}
		}
		System.out.print(out);
	}

	public static Point toPoint(int[] v) {
		return new Point(v[0], v[1]);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

}
