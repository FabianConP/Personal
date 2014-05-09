package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11450_Wedding_shopping {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				int mc[] = readInts(in.readLine()), m = mc[0], c = mc[1];
				boolean[][] cr = new boolean[m + 1][c];
				int aux[], ans = -1;
				for (int j = 0; j < c; j++) {
					aux = readInts(in.readLine());
					if (j == 0) {
						for (int it = 1; it < aux.length; it++)
							if (m - aux[it] >= 0)
								cr[m - aux[it]][0] = true;
					} else
						for (int it = 1; it < aux.length; it++)
							for (int p = m - aux[it]; p >= 0; p--)
								cr[p][j] |= cr[p + aux[it]][j - 1];
				}
				for (int j = 0; j <= m; j++)
					if (cr[j][c - 1]) {
						ans = m - j;
						break;
					}
				if (ans == -1)
					out.append("no solution\n");
				else
					out.append(ans + "\n");
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
