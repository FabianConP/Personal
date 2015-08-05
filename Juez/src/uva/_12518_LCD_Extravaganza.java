package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _12518_LCD_Extravaganza {

	private static class D {
		int s, e, n, f;

		public D(int s, int e, int n, int f) {
			this.s = s;
			this.e = e;
			this.n = n;
			this.f = f;
		}

		@Override
		public String toString() {
			return "D [s=" + s + ", e=" + e + ", n=" + n + ", f=" + f + "]";
		}

	}

	public static int upperBound(int key) {
		int lower = 0, upper = dig.length - 1, ans = -1;
		while (lower <= upper) {
            int mid = (lower + upper) / 2;
            if (key < dig[mid].s)
                upper = mid - 1;
            else {
                lower = mid + 1;
                ans = mid;
            }
        }
		if (ans >= 0 && key >= dig[ans].s && key <= dig[ans].e)
			return ans;
		return -1;
	}

	public static D[] dig;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			dig = new D[n];
			int s = 0;
			for (int i = 0; i < n; i++) {
				int[] df = readInts(in.readLine().trim());
				int d = df[0], f = df[1];
				dig[i] = new D(s, s + f + 1, d, f);
				s += f + 3;
			}
			int m = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < m; i++) {
				int[] rc = readInts(in.readLine());
				int r = rc[0], c = rc[1];
				int d = upperBound(r);
				if (d < 0)
					out.append(".\n");
				else
					out.append(solve(dig[d].n, r - dig[d].s, c, dig[d].f) + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}

	public static char solve(int n, int r, int c, int f) {
		switch (n) {
		case 0:
			return zero(r, c, f);
		case 1:
			return one(r, c, f);
		case 2:
			return two(r, c, f);
		case 3:
			return three(r, c, f);
		case 4:
			return four(r, c, f);
		case 5:
			return five(r, c, f);
		case 6:
			return six(r, c, f);
		case 7:
			return seven(r, c, f);
		case 8:
			return eight(r, c, f);
		case 9:
			return nine(r, c, f);
		}
		return 'X';
	}

	public static boolean s1(int r, int c, int f) {
		return r == 0 && c >= f && c <= (f * 2) - 1;
	}
	
	public static boolean s2(int r, int c, int f) {
		return r == 0 && c >= 0 && c <= f - 1;
	}

	public static boolean s3(int r, int c, int f) {
		return c == 0 && r >= 1 && r <= f;
	}

	public static boolean s4(int r, int c, int f) {
		return r == f + 1 && c >= 0 && c <= f - 1;
	}

	public static boolean s5(int r, int c, int f) {
		return r == f + 1 && c >= f && c <= (f * 2) - 1;
	}

	public static boolean s6(int r, int c, int f) {
		return c == f * 2 && r >= 1 && r <= f;
	}

	public static boolean s7(int r, int c, int f) {
		return c == f && r >= 1 && r <= f;
	}

	public static char zero(int r, int c, int f) {
		if (s1(r, c, f) || s2(r, c, f) || s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f))
			return '_';
		return '.';
	}

	public static char one(int r, int c, int f) {
		if (s4(r, c, f) || s5(r, c, f))
			return '|';
		return '.';
	}

	public static char two(int r, int c, int f) {
		if (s2(r, c, f) || s5(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}

	public static char three(int r, int c, int f) {
		if (s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}

	public static char four(int r, int c, int f) {
		if (s1(r, c, f) || s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s7(r, c, f))
			return '_';
		return '.';
	}

	public static char five(int r, int c, int f) {
		if (s1(r, c, f) || s4(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}

	public static char six(int r, int c, int f) {
		if (s1(r, c, f) || s2(r, c, f) || s4(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}

	public static char seven(int r, int c, int f) {
		if (s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s6(r, c, f))
			return '_';
		return '.';
	}

	public static char eight(int r, int c, int f) {
		if (s1(r, c, f) || s2(r, c, f) || s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}

	public static char nine(int r, int c, int f) {
		if (s1(r, c, f) || s4(r, c, f) || s5(r, c, f))
			return '|';
		else if (s3(r, c, f) || s6(r, c, f) || s7(r, c, f))
			return '_';
		return '.';
	}
}
