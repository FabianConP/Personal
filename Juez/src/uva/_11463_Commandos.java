package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _11463_Commandos {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int tests = Integer.parseInt(line.trim());
			for (int i = 0; i < tests; i++) {
				int build = Integer.parseInt(in.readLine().trim());
				Floyd f = new Floyd();
				f.inint(build);
				int paths = Integer.parseInt(in.readLine().trim()), uv[];
				for (int j = 0; j < paths; j++) {
					uv = retInts(in.readLine());
					f.d[uv[0]][uv[1]] = 1;
					f.d[uv[1]][uv[0]] = 1;
				}
				uv = retInts(in.readLine());
				int start = uv[0], end = uv[1];
				f.floyd();
				long max = 0;
				for (int j = 0; j < build; j++) 
					max = Math.max(max, f.d[start][j]+f.d[j][end]);
				out.append("Case "+times++ + ": "+max+"\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out); 
	}

	public static int[] retInts(String line) {
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

