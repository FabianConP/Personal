package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10312_Expression_Bracketing {

	static long[] cat, scat;

	static void solve(int n) {
		scat = new long[n + 1];
		scat[0] = scat[1] = scat[2] = 1;
		for (int i = 3; i <= n; i++)
			scat[i] = ((6 * i - 9) * scat[i - 1] - (i - 3) * scat[i - 2]) / i;
		cat = new long[n + 1];
		cat[0] = 1;
		for (int i = 1; i <= n; i++)
			cat[i] = (2 * (2 * i - 1) * cat[i - 1]) / (i + 1)	;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		solve(26);
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			out.append((scat[n] - cat[n - 1]) + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}

	public static long[] readLongs(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		long a[] = new long[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Long.parseLong(st.nextToken());
		return a;
	}

	public static double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		double a[] = new double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
