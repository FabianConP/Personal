package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _833_Water_Falls {

	static boolean check(long[] s, long x, long y) {
		return x >= s[0] && x <= s[2] && y > Math.min(s[1], s[3]);
	}

	static double m(long[] s) {
		return (s[3] - s[1]) / (1.0 * s[2] - s[0]);
	}

	static double t(long[] s, long x, long y) {
		double m = m(s);
		double yf = m * (x - s[0]) + Math.min(s[1], s[3]);
		return y - yf;
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
			long[][] s = new long[n][];
			for (int i = 0; i < n; i++) {
				s[i] = readLongs(in.readLine());
				if (s[i][0] > s[i][2]) {
					long aux = s[i][0];
					s[i][0] = s[i][2];
					s[i][2] = aux;
					aux = s[i][1];
					s[i][1] = s[i][3];
					s[i][3] = aux;
				}
			}
			int q = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < q; i++) {
				long[] xy = readLongs(in.readLine());
				long x = xy[0], y = xy[1];
				boolean touch = false;
				do {
					double min = Double.MAX_VALUE, cur = 0;
					int index = 0;
					touch = false;
					for (int j = 0; j < n; j++)
						if (check(s[j], x, y)) {
							cur = t(s[j], x, y);
							touch = true;
							if (cur < min) {
								min = cur;
								index = j;
							}
						}
					if (touch)
						if (m(s[index]) < 0) {
							x = s[index][2];
							y = s[index][3];
						} else {
							x = s[index][0];
							y = s[index][1];
						}
				} while (touch);
				out.append(x + "\n");
			}
		}
		System.out.print(out);
	}

	static long[] readLongs(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		long a[] = new long[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Long.parseLong(st.nextToken());
		return a;
	}
}
