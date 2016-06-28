package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _10585_Center_of_symmetry {

	static double eps = 1e-10;

	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point rot180(Point o) {
			return new Point(-x + 2 * o.x, -y + 2 * o.y);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(x);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(y);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Point o = (Point) obj;
			return Math.abs(o.x - x) < eps && Math.abs(o.y - y) < eps;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		int oo = Integer.MAX_VALUE;
		for (int i = 0; i < nCases; i++) {
			int n = Integer.parseInt(in.readLine().trim());
			Point[] p = new Point[n];
			int xmin = oo, indexmin = 0, xmax = -oo, indexmax = 0;
			HashSet<Point> set = new HashSet<>();
			for (int j = 0; j < n; j++) {
				int[] xy = readInts(in.readLine());
				p[j] = new Point(xy[0], xy[1]);
				if (xmin > xy[0]) {
					xmin = xy[0];
					indexmin = j;
				}
				if (xmax < xy[0]) {
					xmax = xy[0];
					indexmax = j;
				}
				set.add(p[j]);
			}
			double x0 = (p[indexmin].x + p[indexmax].x) / 2;
			double y0 = (p[indexmin].y + p[indexmax].y) / 2;
			boolean check = true;
			Point o = new Point(x0, y0);
			for (int j = 0; j < n; j++)
				if (!set.contains(p[j].rot180(o))) {
					check = false;
					break;
				}
			out.append(check ? "yes\n" : "no\n");
		}
		System.out.print(out);
	}

	static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
