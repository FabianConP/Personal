package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _837_Light_and_Transparencies {

	static final double oo = Double.MAX_VALUE / 2;
	static final double eps = 1e-10;

	static String f(double v) {
		if (Math.abs(v + oo) < eps)
			return "-inf";
		else if (Math.abs(v - oo) < eps)
			return "+inf";
		return String.format(Locale.US, "%.3f", v);
	}

	static String f(double a, double b, double c) {
		return f(a) + " " + f(b) + " " + f(c);
	}

	static class P implements Comparable<P> {
		double x, c;
		boolean d;

		public P(double x, double c, boolean d) {
			this.x = x;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(P o) {
			return Double.compare(x, o.x);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			in.readLine();
			if (nCase != 0)
				out.append('\n');
			int n = Integer.parseInt(in.readLine().trim());
			ArrayList<P> all = new ArrayList<>();
			TreeSet<Double> set = new TreeSet<>();
			double min = oo, max = -oo;
			for (int i = 0; i < n; i++) {
				double[] s = readDoubles(in.readLine());
				if (s[0] > s[2]) {
					double aux = s[0];
					s[0] = s[2];
					s[2] = aux;
					aux = s[1];
					s[1] = s[3];
					s[3] = aux;
				}
				all.add(new P(s[0], s[4], false));
				all.add(new P(s[2] + eps, 1 / s[4], true));
				set.add(s[0]);
				set.add(s[2]);
				min = Math.min(min, s[0]);
				max = Math.max(max, s[2]);
			}
			Collections.sort(all);
			StringBuilder out2 = new StringBuilder();
			out2.append(f(-oo, min, 1) + "\n");
			double mul = 1;
			int cnt = 2;
			for (int i = 0; i < all.size() - 1; i++) {
				P cur = all.get(i);
				mul *= cur.c;
				double next = set.higher(cur.x);
				if (!cur.d) 
					out2.append(f(cur.x, next, mul) + "\n");
				else
					out2.append(f(cur.x-eps, next, mul) + "\n");
				cnt++;
			}
			out2.append(f(max, oo, 1) + "\n");
			out.append(cnt + "\n" + out2);
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
