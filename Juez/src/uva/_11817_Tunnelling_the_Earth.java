package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11817_Tunnelling_the_Earth {

	public static final double RO = 6371009.0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int t = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				double[] v = readDoubles(in.readLine());
				double la0, lo0, la1, lo1;
				la0 = toR(v[0]);
				lo0 = toR(v[1]);
				la1 = toR(v[2]);
				lo1 = toR(v[3]);
				double disEspheric = disEspherical(la0, lo0, la1, lo1);
				double disTunnel = disTunnel(la0, lo0, la1, lo1);
				long ans = Math.round(Math.abs(disEspheric - disTunnel));
				out.append(ans + "\n");
			}
		}
		System.out.print(out);
	}

	public static double disEspherical(double la0, double lo0, double la1,
			double lo1) {
		double a, b, c;
		a = haversine(la1 - la0);
		b = Math.cos(la0) * Math.cos(la1) * haversine(lo1 - lo0);
		c = 2 * Math.atan2(Math.sqrt(a + b), Math.sqrt(1 - a - b));
		return RO * c;
	}

	public static double disTunnel(double la0, double lo0, double la1,
			double lo1) {
		double x0, y0, z0, x1, y1, z1;
		x0 = Math.cos(la0) * Math.cos(lo0);
		x1 = Math.cos(la1) * Math.cos(lo1);
		y0 = Math.cos(la0) * Math.sin(lo0);
		y1 = Math.cos(la1) * Math.sin(lo1);
		z0 = Math.sin(la0);
		z1 = Math.sin(la1);
		return RO
				* Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1)
						+ (z0 - z1) * (z0 - z1));
	}

	public static double haversine(double x) {
		return (1.0 - Math.cos(x)) / 2.0;
	}

	public static double toR(double d) {
		return Math.toRadians(d);
	}

	public static double[] readDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Double.parseDouble(w[i].trim());
		return a;
	}
}
