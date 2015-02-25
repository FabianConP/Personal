package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

public class _815_Flooded {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int region = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			if (v[0] == 0 && v[1] == 0)
				break;
			int d[] = new int[v[0] * v[1]], id = 0;
			for (int i = 0; i < v[0]; i++) {
				int[] row = readInts(in.readLine());
				for (int j = 0; j < row.length; j++)
					d[id++] = row[j];
			}
			Arrays.sort(d);
			long water = Long.parseLong(in.readLine().trim());
			int f = 1;
			double level = d[0], per = 0;
			while (water > 0 && f < d.length) 
				if ((d[f] - d[f - 1]) * f * 100 <= water) {
					water -= (d[f] - d[f - 1]) * f++ * 100;
				} else 
					break;
			level = d[f-1]+(water/(f*100.0));
			per = (f*100.0)/d.length;
			out.append("Region "+region+++"\n");
			out.append("Water level is "+String.format(Locale.US,"%.2f", level)+" meters.\n");
			out.append(String.format(Locale.US,"%.2f", per)+" percent of the region is under water.\n\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
