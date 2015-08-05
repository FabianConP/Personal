package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12519_The_Farnsworth_Parabox {
	
	public static final int INF = (Integer.MAX_VALUE - 1) / 2;
	public static final int MAX = 101;
	public static int d[][] = new int[MAX][MAX];

	public static void inint(int n) {
		for (int i = 0; i < n; i++)
			for (int k = 0; k < n; k++)
				if (i != k)
					d[i][k] = INF;
				else
					d[i][k] = 0;
	}

	public static void floyd(int n) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (d[i][j] > d[i][k] + d[k][j])
						d[i][j] = d[i][k] + d[k][j];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nb = readInts(line);
			int n = nb[0], b = nb[1];
			if (n == 0 && b == 0)
				break;
			inint(n);
			for (int i = 0; i < b; i++) {
				int[] box = readInts(in.readLine());
				int u = box[0] - 1, v = box[1] - 1, w = box[2];
				d[u][v] = Math.min(d[u][v], -w);
				d[v][u] = Math.min(d[v][u], w);
			}
			floyd(n);
			boolean cycle = false;
			for (int i = 0; i < n && !cycle; i++)
				cycle |= d[i][i] < 0;
			out.append(cycle ? "Y\n" : "N\n");
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
}
