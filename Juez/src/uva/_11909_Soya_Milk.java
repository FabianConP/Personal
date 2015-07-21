package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _11909_Soya_Milk {

	static final double PI = Math.PI;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			double[] d = readDoubles(line);
			double l = d[0], w = d[1], h = d[2], t = d[3];
			double a, b, c, aEmpty, aBox, ans;
			if ((l * Math.tan(Math.toRadians(t))) < h) {
				t = Math.toRadians(t);
				c = l;
				b = c / Math.cos(t);
				a = b * Math.sin(t);
				aEmpty = (c * a) / 2;
				aBox = l * h;
				ans = (aBox - aEmpty) * w;
			} else {
				t = Math.toRadians(90 - t);
				c = h;
				a = c * Math.tan(t);
				b = Math.sqrt((c * c) + (a * a));
				ans = (c * a * w) / 2;
			}
			out.append(format(ans) + "\n");
		}
		System.out.print(out);
	}

	public static String format(double a) {
		return String.format(Locale.US, "%.3f mL", a);
	}

	public static double[] readDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Double.parseDouble(w[i]);
		return a;
	}
}
