package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _10341_Solve_It {

	public static final double E = 2.718281828459045;
	public static final double EPS = 1e-7;
	public static double[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			v = retDoubles(line);
			if ((f(v, 0) * f(v, 1)) <= 0)
				out.append(String.format(Locale.US, "%.4f", NetwonRaphson())
						+ "\n");
			else
				out.append("No solution\n");
		}
		System.out.print(out);
	}

	public static double[] retDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Double.parseDouble(w[i].trim());
		return a;
	}

	public static double f(double[] v, double x) {
		return v[0] * Math.pow(E, -x) + v[1] * Math.sin(x) + v[2] * Math.cos(x)
				+ v[3] * Math.tan(x) + v[4] * x * x + v[5];
	}

	public static double ff(double[] v, double x) {
		return (-v[0]) * Math.pow(E, -x) + v[1] * Math.cos(x) - v[2]
				* Math.sin(x) + (v[3] * (1 / Math.cos(x)) * (1 / Math.cos(x)))
				+ 2.0 * v[4] * x;
	}

	static double NetwonRaphson() {
		if (f(v, 0) == 0)
			return 0;
		double xn = 0.5, x = 0;
		while (true) {
			x = xn - f(v, xn) / ff(v, xn);
			if (Math.abs(x - xn) < EPS)
				return xn;
			xn = x;
		}
	}
}
