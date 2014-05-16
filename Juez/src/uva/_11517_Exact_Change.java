package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11517_Exact_Change {
	public static int V[], INF = Integer.MAX_VALUE / 2;

	public static boolean[][] cr;
	public static int[][] minCost;

	public static int fillMinCost(int size, int amount) {
		minCost = new int[V.length][size + 1];
		for (int i = 0; i < minCost.length; i++)
			Arrays.fill(minCost[i], INF);
		cr = new boolean[V.length][size + 1];
		int index = INF;
		for (int i = 0; i < V.length; i++) {
			if (i == 0) {
				minCost[0][0] = 0;
				cr[0][0] = true;
				cr[0][V[i]] = true;
				minCost[0][V[i]] = 1;
				if (V[i] >= amount)
					index = V[i];
			} else
				for (int j = 0; j <= size && j <= index; j++) {
					if (j - V[i] >= 0 && cr[i - 1][j - V[i]]) {
						cr[i][j] = true;
						minCost[i][j] = min(minCost[i - 1][j], minCost[i - 1][j
								- V[i]] + 1, minCost[i][j]);
					} else {
						cr[i][j] = cr[i - 1][j];
						minCost[i][j] = minCost[i - 1][j];
					}
					if (j >= amount && minCost[i][j] != INF) {
						index = j;
						if (i == V.length - 1)
							return j;
					}
				}
		}
		return index;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			int amount = 0;
			for (int i = 0; i < times; i++) {
				amount = Integer.parseInt(in.readLine().trim());
				int nCoins = Integer.parseInt(in.readLine().trim()), sum = 0;
				V = new int[nCoins];
				for (int j = 0; j < nCoins; j++) {
					V[j] = Integer.parseInt(in.readLine().trim());
					if (sum < amount)
						sum += V[j];
				}
				Arrays.sort(V);
				int index = fillMinCost(sum, amount);
				out.append(index + " " + minCost[nCoins - 1][index] + "\n");
			}
		}
		System.out.print(out);
	}

	public static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

}
