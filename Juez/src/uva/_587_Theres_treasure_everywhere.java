package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _587_Theres_treasure_everywhere {
	static String f(double a, double b) {
		return String.format(Locale.US, "(%.3f,%.3f)", a, b);
	}

	static String f(double a) {
		return String.format(Locale.US, "%.3f", a);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			if (line.equals("END"))
				break;
			String[] v = line.substring(0, line.length() - 1).split(",");
			double x = 0, y = 0;
			for (int i = 0; i < v.length; i++) {
				int n = 0, index = 0;
				while (index < v[i].length() && Character.isDigit(v[i].charAt(index)))
					n = (n * 10) + (v[i].charAt(index++) - '0');
				String ins = v[i].substring(index, v[i].length());
				double sqrt = n / Math.sqrt(2);
				switch (ins) {
				case "N":
					y += n;
					break;
				case "NE":
					x += sqrt;
					y += sqrt;
					break;
				case "E":
					x += n;
					break;
				case "SE":
					x += sqrt;
					y -= sqrt;
					break;
				case "S":
					y -= n;
					break;
				case "SW":
					x -= sqrt;
					y -= sqrt;
					break;
				case "W":
					x -= n;
					break;
				case "NW":
					x -= sqrt;
					y += sqrt;
					break;
				}
			}
			out.append("Map #" + nCase++ + "\n");
			out.append("The treasure is located at " + f(x, y) + ".\n");
			out.append("The distance to the treasure is " + f(Math.hypot(x, y)) + ".\n\n");
		}
		System.out.print(out);
	}
}
