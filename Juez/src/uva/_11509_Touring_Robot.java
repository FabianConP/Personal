package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11509_Touring_Robot {

	static class Point {
		long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

	}

	static Point[] p;

	static long cross(Point a, Point b) {
		return a.x * b.y - a.y * b.x;
	}

	static double angle(Point a, Point b) {
		double angle = Math.abs(Math.atan2(a.y, a.x) - Math.atan2(b.y, b.x));
		return angle > Math.PI ? Math.PI * 2 - angle : angle;
	}

	static double turn(Point a, Point b, Point c) {
		Point i = new Point(a.x - b.x, a.y - b.y);
		Point j = new Point(c.x - b.x, c.y - b.y);
		long cross = cross(i, j);
		double angle = angle(i, j);
		if (cross > 0L)
			return Math.PI + angle;
		return Math.PI - angle;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			p = new Point[n];
			for (int i = 0; i < n; i++) {
				long[] xy = readLongs(in.readLine());
				p[i] = new Point(xy[0], xy[1]);
			}
			double total = 0;
			for (int i = 0; i < n; i++)
				total += turn(p[i], p[(i + 1) % n], p[(i + 2) % n]);
			out.append(String.format("%.0f\n", total / (Math.PI * 2)));
		}
		System.out.print(out);
	}

	public static long[] readLongs(String line) {
		String[] w = line.trim().split("\\s+");
		long[] a = new long[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Long.parseLong(w[i]);
		return a;
	}

}
