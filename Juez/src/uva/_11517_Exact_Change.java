package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11517_Exact_Change {
	public static int V[], INF = Integer.MAX_VALUE / 2;

	public static int[] minCost;

	public static int fillMinCost(int size, int amount) {
		minCost = new int[size + 1];
		Arrays.fill(minCost, INF);
		int index =  size;
		minCost[0] = 0;
		for (int i = 0; i < V.length; i++) 
			for (int j = size-V[i]; j >= 0; j--) {
				if (minCost[j] != INF) 
					minCost[j + V[i]] = Math.min(minCost[j + V[i]], minCost[j]+1);
				if (j+ V[i] >= amount && minCost[j+ V[i]] != INF) 
					index = Math.min(j+ V[i],index);
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
				out.append(index + " " + minCost[index] + "\n");
			}
		}
		System.out.print(out);
	}

}
