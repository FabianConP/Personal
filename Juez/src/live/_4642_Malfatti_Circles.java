package live;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _4642_Malfatti_Circles {
	
	/*
	 * Info. links:
	 * General formula: http://en.wikipedia.org/wiki/Malfatti_circles#Radius_formula
	 * Inradius: http://en.wikipedia.org/wiki/Incenter
	 * Area triangle: http://recursostic.educacion.es/descartes/web/materiales_didacticos/formula_heron/formula_de_Heron.htm
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] info = readInts(line);
			if (info[0] == 0 && info[1] == 0 && info[2] == 0 && info[3] == 0
					&& info[4] == 0 && info[5] == 0)
				break;
			Point[] t = new Point[3];
			for (int i = 0; i < info.length; i += 2)
				t[i / 2] = new Point(info[i], info[i + 1]);
			double a = t[0].distance(t[1]);
			double b = t[1].distance(t[2]);
			double c = t[2].distance(t[0]);
			double P = a + b + c;
			double s = P / 2.0;
			double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
			double inR = area / s;
			double inX = (b * t[0].x + c * t[1].x + a * t[2].x) / P;
			double inY = (b * t[0].y + c * t[1].y + a * t[2].y) / P;
			double d = d(t[2].x, inX, t[2].y, inY);
			double e = d(t[0].x, inX, t[0].y, inY);
			double f = d(t[1].x, inX, t[1].y, inY);
			double r1 = (inR / (2 * (s - a))) * (s + d - inR - e - f);
			double r2 = (inR / (2 * (s - b))) * (s + e - inR - d - f);
			double r3 = (inR / (2 * (s - c))) * (s + f - inR - d - e);
			out.append(f(r2) + " " + f(r3) + " " + f(r1) + "\n");
		}
		System.out.print(out);
	}

	public static double d(double a, double b, double c, double d) {
		return Math.sqrt(Math.pow(a - b, 2) + Math.pow(c - d, 2));
	}

	public static String f(double n) {
		return String.format(Locale.US, "%.6f", n);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
