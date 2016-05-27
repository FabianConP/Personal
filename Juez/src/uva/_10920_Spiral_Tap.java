package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10920_Spiral_Tap {

	static boolean between(long a, long b, long c) {
		return a <= c && c <= b;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			long[] nm = readLongs(line);
			long n = nm[1], m = nm[0];
			if (n == 0 && m == 0)
				break;
			long l = (long) Math.ceil(Math.sqrt(n));
			if (l % 2 == 0)
				l++;
			long total = l * l, r, c;
			if (between(total - (l - 1) + 1, total, n)) {
				r = n - (total - (l - 1));
				c = (l - 1);
			} else if (between(total - (l - 1) * 2 + 1, total - l + 1, n)) {
				r = 0;
				c = n - (total - (l - 1) * 2);
			} else if (between(total - (l - 1) * 3 + 1, total - (l - 1) * 2, n)) {
				r = (total - (l - 1) * 2 - n);
				c = 0;
			} else {
				r = l - 1;
				c = (total - (l - 1) * 3 - n);
			}
			r += ((m / 2) - (l / 2));
			c += ((m / 2) - (l / 2));
			out.append("Line = " + ++r + ", column = " + ++c + ".\n");
		}
		System.out.print(out);
	}

	public static long[] readLongs(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		long a[] = new long[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Long.parseLong(st.nextToken());
		return a;
	}

}
