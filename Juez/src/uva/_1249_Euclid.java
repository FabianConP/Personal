package uva;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class _1249_Euclid {

	static Point2D.Double unit(Point2D.Double p) {
		double mag = Math.hypot(p.x, p.y);
		return new Point2D.Double(p.x / mag, p.y / mag);
	}

	static String f(double v) {
		return String.format(Locale.US, "%.3f", v);
	}

	static String f(Point2D.Double... v) {
		String r = "";
		for (int i = 0; i < v.length; i++) {
			if (i != 0)
				r += " ";
			r += f(v[i].x) + " " + f(v[i].y);
		}
		return r;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			double[] v = readDoubles(line);
			boolean allZero = true;
			for (int i = 0; i < v.length; i++)
				if (Math.abs(v[i]) > 1e-10)
					allZero = false;
			if (allZero)
				break;
			Point2D.Double a = new Point2D.Double(v[0], v[1]);
			Point2D.Double b = new Point2D.Double(v[2], v[3]);
			Point2D.Double c = new Point2D.Double(v[4], v[5]);
			Point2D.Double d = new Point2D.Double(v[6], v[7]);
			Point2D.Double e = new Point2D.Double(v[8], v[9]);
			Point2D.Double f = new Point2D.Double(v[10], v[11]);
			Point2D.Double ac = unit(new Point2D.Double(c.x - a.x, c.y - a.y));
			Point2D.Double ab = new Point2D.Double(b.x - a.x, b.y - a.y);
			Point2D.Double de = new Point2D.Double(e.x - d.x, e.y - d.y);
			Point2D.Double df = new Point2D.Double(f.x - d.x, f.y - d.y);
			double k = (de.x * df.y - df.x * de.y) / (ac.x * ab.y - ab.x * ac.y);
			k = Math.abs(k) / 2;
			Point2D.Double z = new Point2D.Double(ac.x * k, ac.y * k);
			Point2D.Double h = new Point2D.Double(z.x + a.x, z.y + a.y);
			Point2D.Double g = new Point2D.Double(z.x + b.x, z.y + b.y);
			out.append(f(g, h) + "\n");
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
