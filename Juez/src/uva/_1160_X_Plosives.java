package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class _1160_X_Plosives {

	private static class UnionFind {

		public static int padre[];
		public static int rango[];

		public void init(int v) {
			padre = new int[v];
			rango = new int[v];
			for (int i = 0; i < padre.length; i++) {
				padre[i] = i;
				rango[i] = 1;
			}
		}

		public int find(int x) {
			while (padre[x] != x) {
				padre[x] = padre[padre[x]];
				x = padre[x];
			}
			return x;
		}

		public void union(int a, int b) {
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

		public boolean sameComponent(int a, int b) {
			return find(a) == find(b);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 0;
		int[] ids = new int[100001];
		while ((line = in.readLine()) != null) {
			if (times++ != 0)
				line = in.readLine();
			for (int i = 0; i < ids.length; i++)
				ids[i] = -1;
			int refused = 0, id = 0;
			ArrayList<Point> pairs = new ArrayList<Point>();
			while (!line.equals("-1")) {
				int[] pair = readInts(line);
				if (ids[pair[0]] == -1) 
					ids[pair[0]] = id++;
				if (ids[pair[1]] == -1) 
					ids[pair[1]] = id++;
				pairs.add(new Point(ids[pair[0]], ids[pair[1]]));
				line = in.readLine();
			}
			UnionFind G = new UnionFind();
			G.init(id+1);
			for (int i = 0; i < pairs.size(); i++) {
				Point pair = pairs.get(i);
				if (G.sameComponent(pair.x, pair.y))
					refused++;
				else
					G.union(pair.x, pair.y);
			}
			out.append(refused + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
