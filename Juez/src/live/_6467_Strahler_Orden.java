package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

public class _6467_Strahler_Orden {

	public static ArrayList<Integer>[] ady, ady2;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSets = Integer.parseInt(line.trim());
			for (int i = 0; i < nSets; i++) {
				int[] kmp = readInts(in.readLine()), edge;
				int k = kmp[1], p = kmp[2];
				ady = new ArrayList[k + 1];
				ady2 = new ArrayList[k + 1];
				for (int j = 1; j < ady.length; j++) {
					ady[j] = new ArrayList<Integer>();
					ady2[j] = new ArrayList<Integer>();
				}
				for (int j = 0; j < p; j++) {
					edge = readInts(in.readLine());
					ady[edge[0]].add(edge[1]);
					ady2[edge[1]].add(edge[0]);
				}
				Queue<Integer> q = new LinkedList<Integer>();
				int[] w = new int[k + 1];
				for (int j = 1; j < ady.length; j++)
					if (ady2[j].isEmpty()) {
						q.add(j);
						w[j] = 1;
					}
				int node = 0, curMax = 0, e = 0;
				while (!q.isEmpty()) {
					node = q.poll();
					curMax = 0;
					for (int j = 0; j < ady2[node].size(); j++) {
						e = ady2[node].get(j);
						if (w[e] > curMax)
							curMax = w[e];
						else if (w[e] == curMax)
							curMax++;
					}
					w[node] = Math.max(w[node], curMax);
					for (int j = 0; j < ady[node].size(); j++)
						q.add(ady[node].get(j));
				}
				out.append((i + 1) + " " + w[k] + "\n");
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
