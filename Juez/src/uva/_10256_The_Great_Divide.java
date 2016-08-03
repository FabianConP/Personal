package uva;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class _10256_The_Great_Divide {
	public static Point root;

	public static class Point implements Comparable<Point> {

		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			if (y == p.y) {
				return x < p.x ? -1 : x == p.x ? 0 : 1;
			}
			return y < p.y ? -1 : y == p.y ? 0 : 1;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static class CounterClockwiseComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			int det = (p1.x - root.x) * (p2.y - root.y) - (p2.x - root.x) * (p1.y - root.y);
			if (det == 0) {
				int dis1 = Math.abs((p1.x - root.x) * (p1.x - root.x) + (p1.y - root.y) * (p1.y - root.y));
				int dis2 = Math.abs((p2.x - root.x) * (p2.x - root.x) + (p2.y - root.y) * (p2.y - root.y));
				return dis1 < dis2 ? -1 : dis1 == dis2 ? 0 : 1;
			}
			return det > 0 ? -1 : 1;
		}
	}

	public static int turn(Point p1, Point p2, Point pivote) {
		int det = (p1.x - pivote.x) * (p2.y - pivote.y) - (p2.x - pivote.x) * (p1.y - pivote.y);
		return det;
	}

	public static Point[] convex_hull(Point[] points) {
		int n = points.length;

		Point minimin = null;
		for (int i = 0; i < n; i++) {
			if (minimin == null || points[i].compareTo(minimin) == -1) {
				minimin = points[i];
			}
		}

		root = minimin;

		Arrays.sort(points, new CounterClockwiseComparator());

		int j;

		Point[] convex_hull = new Point[n];
		j = 0;
		for (int i = 0; i < n; i++) {
			while (i > 1 && j > 1 && turn(points[i], convex_hull[j - 1], convex_hull[j - 2]) >= 0) {
				j--;
			}
			convex_hull[j++] = points[i];
		}
		return Arrays.copyOf(convex_hull, j);

	}

	static int pointInPolygon(Point[] p, int qx, int qy) {
		int n = p.length;
		int cnt = 0;
		for (int i = 0, j = n - 1; i < n; j = i++) {
			if (p[i].y == qy && (p[i].x == qx
					|| p[j].y == qy && (p[i].x <= qx || p[j].x <= qx) && (p[i].x >= qx || p[j].x >= qx)))
				return 0; // boundary
			if ((p[i].y > qy) != (p[j].y > qy)) {
				long det = ((long) p[i].x - qx) * ((long) p[j].y - qy) - ((long) p[j].x - qx) * ((long) p[i].y - qy);
				if (det == 0)
					return 0; // boundary
				if ((det > 0) != (p[j].y > p[i].y))
					++cnt;
			}
		}
		return cnt % 2 == 0 ? -1 /* exterior */ : 1 /* interior */;
	}

	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			int[] nm = readInts(line);
			int n = nm[0], m = nm[1];
			if (n == 0 && m == 0)
				break;
			Point[] a = new Point[n];
			Point[] b = new Point[m];
			for (int i = 0; i < n; i++) {
				int[] xy = readInts(in.readLine());
				a[i] = new Point(xy[0], xy[1]);
			}
			for (int i = 0; i < m; i++) {
				int[] xy = readInts(in.readLine());
				b[i] = new Point(xy[0], xy[1]);
			}
			Point[] ca = convex_hull(a);
			Point[] cb = convex_hull(b);
			n = ca.length;
			m = cb.length;
			boolean check = true;
			for (int i = 0; i < ca.length && check; i++)
				for (int j = 0; j < cb.length && check; j++)
					if (Line2D.Double.linesIntersect(ca[i].x, ca[i].y, ca[(i + 1) % n].x, ca[(i + 1) % n].y, cb[j].x,
							cb[j].y, cb[(j + 1) % m].x, cb[(j + 1) % m].y))
						check = false;
			for (int i = 0; i < ca.length && check; i++)
				if (pointInPolygon(cb, ca[i].x, ca[i].y) >= 0)
					check = false;
			for (int i = 0; i < cb.length && check; i++)
				if (pointInPolygon(ca, cb[i].x, cb[i].y) >= 0)
					check = false;
			out.append(check ? "Yes\n" : "No\n");
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
