package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _10092_The_Problem_with_the_Problem_Setter {
	static class Edge {
		int t, rev, cap, f;

		public Edge(int t, int rev, int cap) {
			this.t = t;// Target
			this.rev = rev; // Reverse node
			this.cap = cap; // Capacity
		}

		@Override
		public String toString() {
			return "Edge [t=" + t + ", rev=" + rev + ", cap=" + cap + ", f="
					+ f + "]";
		}

	}

	private static class Dinic {

		public ArrayList<Edge>[] ady;
		public int V;

		public Dinic(int v) {
			V = v;
			ady = new ArrayList[V];
			for (int i = 0; i < V; i++)
				ady[i] = new ArrayList<Edge>();
		}

		public void addEdge(int s, int t, int cap) {
			ady[s].add(new Edge(t, ady[t].size(), cap));
			ady[t].add(new Edge(s, ady[s].size() - 1, 0));
		}

		boolean dinicBfs(int src, int dest, int[] dist) {
			Arrays.fill(dist, -1);
			dist[src] = 0;
			int[] Q = new int[V];
			int sizeQ = 0;
			Q[sizeQ++] = src;
			for (int i = 0; i < sizeQ; i++) {
				int u = Q[i];
				for (Edge e : ady[u]) {
					if (dist[e.t] < 0 && e.f < e.cap) {
						dist[e.t] = dist[u] + 1;
						Q[sizeQ++] = e.t;
					}
				}
			}
			return dist[dest] >= 0;
		}

		int dinicDfs(int[] ptr, int[] dist, int dest, int u, int f) {
			if (u == dest)
				return f;
			for (; ptr[u] < ady[u].size(); ++ptr[u]) {
				Edge e = ady[u].get(ptr[u]);
				if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
					int df = dinicDfs(ptr, dist, dest, e.t,
							Math.min(f, e.cap - e.f));
					if (df > 0) {
						e.f += df;
						ady[e.t].get(e.rev).f -= df;
						return df;
					}
				}
			}
			return 0;
		}

		public int maxFlow(int src, int dest) {
			int flow = 0;
			int[] dist = new int[V];
			while (dinicBfs(src, dest, dist)) {
				int[] ptr = new int[V];
				while (true) {
					int df = dinicDfs(ptr, dist, dest, src, Integer.MAX_VALUE);
					if (df == 0)
						break;
					flow += df;
				}
			}
			return flow;
		}
	}

	public static int P; // Letters

	public static int start, target;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			int nc = v[0], np = v[1];
			if (nc == 0 && np == 0)
				break;
			start = 0;
			target = 2 + nc + np;
			P = 1 + nc;
			Dinic G = new Dinic(target + 1);
			int[] cat = readInts(in.readLine());
			for (int c = 1; c <= nc; c++)
				G.addEdge(start, c, cat[c - 1]);
			for (int p = 0; p < np; p++) {
				v = readInts(in.readLine());
				for (int c = 1; c < v.length; c++)
					G.addEdge(v[c], P + p, 1);
				G.addEdge(P + p, target, 1);
			}
			int maxFlow = G.maxFlow(start, target);
			boolean match = true;
			for (int i = 0; i < nc && match; i++)
				if (G.ady[start].get(i).f != cat[i])
					match = false;
			if (match) {
				out.append("1\n");
				for (int c = 1; c <= nc; c++) {
					int col = 0;
					for (int p = 0; p < G.ady[c].size(); p++) {
						Edge e = G.ady[c].get(p);
						if (e.t >= P && e.t <= P + np && e.f != 0) {
							if (col++ != 0)
								out.append(" ");
							out.append(e.t - P + 1);
						}
					}
					out.append("\n");
				}
			} else
				out.append("0\n");
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
