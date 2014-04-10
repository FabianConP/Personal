package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1112_Mice_and_Maze {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				in.readLine();
				int cells = Integer.parseInt(in.readLine().trim());
				Floyd f = new Floyd();
				f.inint(cells + 1);
				int exit = Integer.parseInt(in.readLine().trim());
				int tle = Integer.parseInt(in.readLine().trim());
				int con = Integer.parseInt(in.readLine().trim());
				for (int j = 0; j < con; j++) {
					int[] v = readInts(in.readLine());
					f.d[v[0]][v[1]] = v[2];
				}
				int ans = 0;
				f.floyd();
				for (int j = 1; j < f.d.length; j++)
					if (f.d[j][exit] <= tle)
						ans++;
				if (i != 0)
					out.append("\n");
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

	private static class Floyd {
		public static int d[][];
		public static final int INF = (Integer.MAX_VALUE - 1) / 2;

		public void inint(int n) {
			d = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int k = 0; k < n; k++)
					if (i != k)
						d[i][k] = INF;
		}

		void floyd() {
			int rows = d.length;
			for (int k = 0; k < rows; k++)
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < rows; j++)
						if (d[i][j] > d[i][k] + d[k][j]) {
							d[i][j] = d[i][k] + d[k][j];
						}
		}
	}

}
