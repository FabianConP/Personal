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
				int[][] it = new int[c + 1][m + 1];
				int[][] co = new int[c + 1][m + 1];
				int[] aux;
				boolean pos = true;
				for (int j = 1; j <= c; j++) {
					aux = readInts(in.readLine());
					boolean buyAnItem = false;
					for (int k = 1; k < aux.length && pos; k++) {
						for (int l = aux[k]; l <= m; l++) {
							if(it[j-1][l-aux[k]] == j-1) {
								buyAnItem = true;
								it[j][l] = Math.max(it[j][l], it[j-1][l-aux[k]]+1);
								co[j][l] = Math.max(co[j][l], co[j-1][l-aux[k]]+aux[k]);
							}
						}
					}
					pos&=buyAnItem;
				}
				if(!pos)
					out.append("no solution\n");
				else
					out.append(co[c][m]+"\n");
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
