package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class _10927_Bright_Lights {

	static final double EPS = 1e-11;

	static class Point implements Comparable<Point> {
		int x, y, z;
		double a;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
			a = Math.atan2(y, x);
		}

		public boolean coolinear(Point o) {
			return Math.abs(this.a - o.a) < EPS;
		}

		@Override
		public int compareTo(Point o) {
			if (coolinear(o))
				return Double.compare(Math.hypot(x, y), Math.hypot(o.x, o.y));
			return Double.compare(this.a, o.a);
		}

		@Override
		public String toString() {
			return "x = " + x + ", y = " + y;
		}

	}

	static class SortByX implements Comparator<Point> {

		@Override
		public int compare(Point a, Point b) {
			if (a.x == b.x)
				return a.y - b.y;
			return a.x - b.x;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int nDataSet = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			Point[] p = new Point[n];
			for (int i = 0; i < n; i++) {
				int[] d = readInts(in.readLine());
				p[i] = new Point(d[0], d[1], d[2]);
			}
			Arrays.sort(p);
			ArrayList<Point> ans = new ArrayList<>();
			int end = 0, max = 0;
			for (int i = 0; i < p.length; i++) {
				end = i + 1;
				max = p[i].z;
				while (end < p.length && p[end - 1].coolinear(p[end])) {
					if (max >= p[end].z)
						ans.add(p[end]);
					max = Math.max(max, p[end].z);
					end++;
				}
				if (end != i + 1)
					i = end - 1;
			}
			out.append("Data set " + nDataSet++ + ":\n");
			if (ans.isEmpty())
				out.append("All the lights are visible.\n");
			else {
				out.append("Some lights are not visible:\n");
				Collections.sort(ans, new SortByX());
				for (int i = 0; i < ans.size(); i++) {
					out.append(ans.get(i));
					if (i != ans.size() - 1)
						out.append(";");
					else
						out.append(".");
					out.append("\n");
				}
			}

		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
