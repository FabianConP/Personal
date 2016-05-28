package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class _11503_Virtual_Friends {

	public static int padre[];
	public static int rango[];

	public static void init(int v) {
		padre = new int[v];
		rango = new int[v];
		for (int i = 0; i < padre.length; i++) {
			padre[i] = i;
			rango[i] = 1;
		}
	}

	public static int find(int x) {
		while (padre[x] != x) {
			padre[x] = padre[padre[x]];
			x = padre[x];
		}
		return x;
	}

	public static void union(int a, int b) {
		int xRoot = find(a);
		int yRoot = find(b);
		if (rango[xRoot] > rango[yRoot]) {
			padre[yRoot] = xRoot;
			rango[xRoot] += rango[yRoot];
		} else {
			padre[xRoot] = yRoot;
			rango[yRoot] += rango[xRoot];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < nCases; t++) {
			int r = Integer.parseInt(in.readLine().trim());
			String[][] all = new String[r][2];
			Map<String, Integer> map = new HashMap<>();
			int id = 0;
			for (int i = 0; i < r; i++) {
				all[i] = in.readLine().trim().split(" ");
				for (int j = 0; j < all[i].length; j++)
					if (!map.containsKey(all[i][j]))
						map.put(all[i][j], id++);
			}
			init(id);
			int u, v;
			for (int i = 0; i < r; i++) {
				u = map.get(all[i][0]);
				v = map.get(all[i][1]);
				if (find(u) != find(v))
					union(u, v);
				out.append(rango[find(u)] + "\n");
			}
		}
		System.out.print(out);
	}
}
