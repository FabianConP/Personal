package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _259_Software_Allocation {
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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		Edmons_karp f;
		int source = 0, target = 37;
		while ((line = in.readLine()) != null && line.length() != 0) {
			f = new Edmons_karp(38);
			int totalApp = 0;
			while (line != null && line.length() != 0) {
				String[] info = line.trim().split(" ");
				int app = info[0].charAt(0) - 'A' + 1;
				int amount = info[0].charAt(1) - '0';
				char[] pcs = info[1].toCharArray();
				totalApp += amount;
				f.g[source][app] = amount;
				f.ady[source].add(app);
				f.ady[app].add(source);
				for (int i = 0; i < pcs.length - 1; i++) {
					int pc = pcs[i] - '0' + 27;
					f.g[app][pc] = 1;
					f.ady[app].add(pc);
					f.ady[pc].add(app);
					if (f.g[pc][target] == 0) {
						f.ady[pc].add(target);
						f.ady[target].add(pc);
					}
					f.g[pc][target] = 1;
				}
				line = in.readLine();
			}
			int maxFlow = f.edmons_karp(source, target);
			if (maxFlow != totalApp)
				out.append("!");
			else {
				for (int i = 27; i <= 36; i++) {
					boolean used = false;
					for (int j = 1; j <= 26; j++)
						if (f.g[i][j] == 1) {
							out.append((char) (j + 'A' - 1));
							used = true;
							break;
						}
					out.append(!used ? "_" : "");
				}
			}
			out.append("\n");
		}
		System.out.print(out);
	}
}
