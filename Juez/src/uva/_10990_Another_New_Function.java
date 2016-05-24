package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10990_Another_New_Function {

	static int sum[];

	static void sieve(int max) {
		long[][] s = new long[max + 1][2];
		boolean[] v = new boolean[max + 1];
		int[] depth = new int[max + 1];
		for (int i = 0; i <= max; i++)
			s[i][0] = s[i][1] = 1;
		for (int i = 2; i <= max; i++)
			if (!v[i]) {
				s[i][0] = s[i][1] = 1;
				for (int j = i; j <= max; j += i) {
					s[j][0] *= (i - 1);
					s[j][1] *= i;
					v[j] = true;
				}
			}
		sum = new int[max + 1];
		depth[2] = 1;
		for (int i = 3; i <= max; i++)
			depth[i] = 1 + depth[(int) ((i * s[i][0]) / s[i][1])];
		for (int i = 2; i <= max; i++)
			sum[i] = sum[i - 1] + depth[i];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		sieve(2000000);
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < nCases; t++) {
			int[] nm = readInts(in.readLine());
			out.append((sum[nm[1]] - sum[nm[0] - 1]) + "\n");
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
}
