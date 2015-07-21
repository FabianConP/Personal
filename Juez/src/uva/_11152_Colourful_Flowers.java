package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _11152_Colourful_Flowers {

	static final double PI = Math.PI;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			double[] abc = readDoubles(line);
			double a = abc[0], b = abc[1], c = abc[2];
			double s = (a + b + c) / 2;
			double aVio = (Math.sqrt(s * (s - a) * (s - b) * (s - c)));
			double rsun = (a * b * c) / (4 * aVio);
			double rros = aVio / s;
			double aSun = PI * rsun * rsun;
			double aRos = PI * rros * rros;
			aSun -= aVio;
			aVio -= aRos;
			out.append(formatAll(aSun, aVio, aRos) + "\n");
		}
		System.out.print(out);
	}

	public static String formatAll(double a, double b, double c) {
		return format(a) + " " + format(b) + " " + format(c);
	}

	public static String format(double a) {
		return String.format(Locale.US, "%.4f", a);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}

	public static double[] readDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Double.parseDouble(w[i]);
		return a;
	}
}
