package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _11045_My_T_shirt_suits_me {

	private static class Edmons_karp {
		public static int INF = 200000000;
		public int g[][];
		public ArrayList<Integer>[] ady;
		public int mf, f, s;// max flow, actually flow, source node,
		public int V;// number of vertex
		public int[] p;

		public Edmons_karp(int v) {
			V = v;
			g = new int[V][V];
			p = new int[V];
			ady = new ArrayList[V];
			for (int i = 0; i < ady.length; i++)
				ady[i] = new ArrayList<Integer>(V);
		}

		private void augment(int v, int minEdge) {
			if (v == s) {
				f = minEdge;
				return;
			} else if (p[v] != -1) {
				augment(p[v], Math.min(minEdge, g[p[v]][v]));
				g[p[v]][v] -= f;
				g[v][p[v]] += f;
			}
		}

		public int edmons_karp(int s, int t) {
			int u, e;
			mf = 0;
			this.s = s;
			Arrays.fill(p, -1);
			while (true) {
				f = 0;
				Queue<Integer> q = new LinkedList<Integer>();
				int[] dist = new int[V];
				Arrays.fill(dist, INF);
				dist[s] = 0;
				q.offer(s);

				Arrays.fill(p, -1);

				while (!q.isEmpty()) {
					u = q.poll();
					if (u == t)
						break;
					for (int i = 0; i < ady[u].size(); i++) {
						e = ady[u].get(i);
						if (g[u][e] > 0 && dist[e] == INF) {
							dist[e] = dist[u] + 1;
							q.offer(e);
							p[e] = u;// parent of vertex e is vertex u
						}
					}
				}
				augment(t, INF);
				if (f == 0)
					break;
				mf += f;
			}
			return mf;
		}
	}

	public static HashMap<String, Integer> sizes;

	public static void fillSizes() {
		sizes = new HashMap<String, Integer>(6);
		String[] s = { "XXL", "XL", "L", "M", "S", "XS" };
		for (int i = 0; i < s.length; i++)
			sizes.put(s[i], i + 31);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillSizes();
		Edmons_karp f;
		int source = 0, target = 37;
		int nTest = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < nTest; i++) {
			int[] nm = readInts(in.readLine());
			int n = nm[0], m = nm[1];
			f = new Edmons_karp(38);
			for (int vol = 1; vol <= m; vol++) {
				String[] s = in.readLine().trim().split(" ");
				f.g[source][vol] = 1;
				f.ady[source].add(vol);
				f.ady[vol].add(source);
				for (int k = 0; k < s.length; k++) {
					int size = sizes.get(s[k]);
					f.g[vol][size] = 1;
					f.ady[vol].add(size);
					f.ady[size].add(vol);
					if (f.g[size][target] == 0) {
						f.ady[size].add(target);
						f.ady[target].add(size);
					}
					f.g[size][target] = n / 6;
				}
			}
			int maxFlow = f.edmons_karp(source, target);
			out.append(maxFlow == m ? "YES\n" : "NO\n");
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
