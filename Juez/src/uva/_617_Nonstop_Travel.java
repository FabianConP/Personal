package uva;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class _617_Nonstop_Travel {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1, n;
		while ((n = in.nextInt()) != -1) {
			double[] d = new double[n];
			double[][] ac = new double[n][3];
			for (int i = 0; i < n; i++) {
				d[i] = in.nextDouble() * 3600D;
				ac[i][0] = in.nextInt();
				ac[i][1] = in.nextInt();
				ac[i][2] = in.nextInt();
			}
			double time = 0, ini, mod, dif;
			ArrayList<Point> periods = new ArrayList<Point>(61);
			int s = -1, e = -1;
			for (int v = 30; v <= 60; v++) {
				boolean pass = true;
				for (int j = 0; j < n; j++) {
					time = d[j] / v;
					mod = ac[j][0] + ac[j][1] + ac[j][2];
					ini = Math.floor(time / mod);
					dif = time - ini * mod;
					if ((dif - ac[j][0] - ac[j][1]) > 1e-12) {
						pass = false;
						break;
					}
				}
				if (pass) {
					if (e != -1) {
						e = v;
					} else {
						if (e != -1)
							periods.add(new Point(s, e));
						s = e = v;
					}
				} else {
					if (e != -1)
						periods.add(new Point(s, e));
					e = -1;
				}
			}
			if (e != -1)
				periods.add(new Point(s, e));
			out.append("Case " + times++ + ": ");
			Point p;
			for (int i = 0; i < periods.size(); i++) {
				p = periods.get(i);
				if (i != 0)
					out.append(", ");
				if (p.x != p.y)
					out.append(p.x + "-" + p.y);
				else
					out.append(p.x);
			}
			if (periods.isEmpty())
				out.append("No acceptable speeds.");
			out.append("\n");
		}
		System.out.print(out);
	}
}
