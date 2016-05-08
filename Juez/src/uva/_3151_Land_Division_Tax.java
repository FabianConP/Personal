package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class _3151_Land_Division_Tax {

	static int l[], dp[][];

	static int s(int s, int e, int r) {
		if (dp[s][e] != -1)
			return dp[s][e];
		if (s >= e)
			return 0;
		int min = Integer.MAX_VALUE, div, acum = 0;
		for (int i = s; i < e; i++) {
			acum += l[i];
			div = Math.max(acum, r - acum);
			min = Math.min(min, div + s(s, i, acum) + s(i + 1, e, r - acum));
		}
		return dp[s][e] = min;
	}

	public static void main(String[] args) throws IOException {
		File inputFile = new File("entradaA");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			double[] nf = readDoubles(line);
			int n = (int) Math.round(nf[0]);
			double f = nf[1];
			if (n == 0 && Math.abs(f) < 1e-6)
				break;
			int[] ll = readInts(in.readLine());
			l = new int[n * 2 - 1];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if (i + 1 != n)
					l[i + n] = ll[i];
				l[i] = ll[i];
				sum += l[i];
			}
			dp = new int[n * 2 - 1][n * 2 - 1];
			for (int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], -1);
				dp[i][i] = 0;
			}
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < n - 1; i++)
				ans = Math.min(ans, s(i, i + n - 1, sum));
			if(n == 1)
				ans = 0;
			out.append(String.format(Locale.US, "%.2f", ans*f) + "\n");
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

	public static double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		double a[] = new double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
