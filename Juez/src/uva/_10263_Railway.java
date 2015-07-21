package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _10263_Railway {

	static final double EPS = 1e-11;

	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}

	}

	static Point minPointSeg(Point a, Point b, Point c) {
		Point d = new Point(0, 0);
		Point ab = new Point(b.x - a.x, b.y - a.y);
		Point ac = new Point(c.x - a.x, c.y - a.y);
		double s = ab.x * ab.x + ab.y * ab.y;
		double p = ((ab.x * ac.x) + (ab.y * ac.y)) / s;
		if (p > 1)
			p = 1;
		else if (p < 0)
			p = 0;
		d = new Point(a.x + p * ab.x, a.y + p * ab.y);
		return d;
	}

	static double dis(Point a, Point b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			double xm = Double.parseDouble(line.trim());
			double ym = Double.parseDouble(in.readLine().trim());
			Point m = new Point(xm, ym);
			int n = Integer.parseInt(in.readLine().trim());
			Point[] s = new Point[n + 1];
			for (int i = 0; i < n + 1; i++) {
				double x = Double.parseDouble(in.readLine().trim());
				double y = Double.parseDouble(in.readLine().trim());
				s[i] = new Point(x, y);
			}
			double dis = Double.MAX_VALUE, d;
			Point ans = new Point(0, 0);
			for (int i = 1; i < n + 1; i++) {
				Point per = minPointSeg(s[i - 1], s[i], m);
				d = dis(per, m);
				if (d < dis) {
					dis = d;
					ans = per;
				}
			}
			out.append(f(ans.x) + "\n" + f(ans.y) + "\n");
		}
		System.out.print(out);
	}

	static String f(double v) {
		return String.format(Locale.US, "%.4f", v);
	}

	public static double[] readDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Double.parseDouble(w[i]);
		return a;
	}
}
