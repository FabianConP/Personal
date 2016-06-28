package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class _10250_The_Other_Two_Trees {

	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point rot90() {
			return new Point(-y, x);
		}

		@Override
		public String toString() {
			return String.format(Locale.US, "%.10f", x) + " " + String.format(Locale.US, "%.10f", y);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			double[] v = readDoubles(line);
			Point a = new Point(v[0], v[1]);
			Point b = new Point(v[2], v[3]);
			Point o = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
			Point aa = (new Point(a.x - o.x, a.y - o.y)).rot90();
			Point bb = (new Point(b.x - o.x, b.y - o.y)).rot90();
			aa.x += o.x;
			aa.y += o.y;
			bb.x += o.x;
			bb.y += o.y;
			out.append(aa + " " + bb + "\n");
		}
		System.out.print(out);
	}

	static double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		double a[] = new double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
