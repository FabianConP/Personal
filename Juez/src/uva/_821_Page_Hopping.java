package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _821_Page_Hopping {

	public static class Floyd {
		public static int distancias[][];
		public static int padres[][];
		public static final int INF = (Integer.MAX_VALUE - 1) / 2;

		public void inint(int n) {
			distancias = new int[n][n];
			padres = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int k = 0; k < n; k++)
					if (i != k)
						distancias[i][k] = INF;
		}

		void floyd(int rows) {

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < rows; j++) {
					padres[i][j] = i;
					if (distancias[i][j] == INF || i == j)
						padres[i][j] = -1;
				}
			}
			for (int k = 0; k < rows; k++)
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < rows; j++)
						if (distancias[i][j] > distancias[i][k]
								+ distancias[k][j]) {
							distancias[i][j] = distancias[i][k]
									+ distancias[k][j];
							padres[i][j] = padres[k][j];
						}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0 || line.equals("0 0"))
				break d;
			int[] v = retInts(line);
			int maxNumberNode = 0;
			for (int i = 0; i < v.length; i++) 
				maxNumberNode = Math.max(maxNumberNode, v[i]);
			Floyd floyd = new Floyd();
			floyd.inint(maxNumberNode+1);
			for (int i = 0; i < v.length - 2; i += 2)
				floyd.distancias[v[i]][v[i + 1]] = 1;
			floyd.floyd(floyd.distancias.length);
			double destino = 0, minDistances = 0;
			for (int i = 0; i < floyd.distancias.length; i++)
				for (int j = 0; j < floyd.distancias.length; j++)
					if (i != j && floyd.distancias[i][j] < 1000000) {
						destino++;
						minDistances += floyd.distancias[i][j];
					}
			System.out.printf(Locale.US, "Case %d: average length between pages = %.3f clicks\n",times++, minDistances/destino);
		} while (line != null && line.length() != 0);

	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] nums = new int[w.length];
		for (int i = 0; i < w.length; i++)
			nums[i] = Integer.parseInt(w[i]);
		return nums;
	}

}
