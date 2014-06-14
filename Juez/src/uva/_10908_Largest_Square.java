package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10908_Largest_Square {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int t = Integer.parseInt(line.trim());
			for (int times = 0; times < t; times++) {
				int[] mnq = readInts(in.readLine());
				int M = mnq[0], N = mnq[1], Q = mnq[2];
				char[][] m = new char[M][N];
				for (int i = 0; i < m.length; i++)
					m[i] = in.readLine().trim().toCharArray();
				boolean squ = true;
				out.append(M + " " + N + " " + Q + "\n");
				int q[], ans = 1, add = 1;
				for (int qi = 0; qi < Q; qi++) {
					q = readInts(in.readLine());
					ans = 1;
					add = 1;
					while (q[0] - add >= 0 && q[0] + add < M && q[1] - add >= 0
							&& q[1] + add < N) {
						squ = true;
						for (int i = q[0] - add; i <= q[0] + add && squ; i++)
							for (int j = q[1] - add; j <= q[1] + add && squ; j++)
								squ &= m[q[0]][q[1]] == m[i][j];
						ans = Math.max(ans, squ ? add * 2 + 1 : 1);
						add ++;
					}
					out.append(ans + "\n");
				}
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
