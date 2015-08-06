package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MTOTALF {

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

	public static int L = 27; // Letters

	public static int start, target;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nEdges = Integer.parseInt(in.readLine().trim());
		start = 1;
		target = 2;
		HashMap<String, Integer> mapNames = new HashMap<String, Integer>();
		mapNames.put("A", start);
		mapNames.put("Z", target);
		ArrayList<String[]> edges = new ArrayList<String[]>(nEdges);
		int idEdge = 3;
		for (int i = 0; i < nEdges; i++) {
			String[] e = in.readLine().trim().split(" ");
			if(!mapNames.containsKey(e[0]))
				mapNames.put(e[0], idEdge++);
			if(!mapNames.containsKey(e[1]))
				mapNames.put(e[1], idEdge++);
			edges.add(e);
		}
		Dinic G = new Dinic(1+mapNames.size());
		G.addEdge(start, 1, Integer.MAX_VALUE/2);
		for (String[] e : edges){
			int u = mapNames.get(e[0]);
			int v = mapNames.get(e[1]);
			int c = Integer.parseInt(e[2]);
			G.addEdge(u, v, c);
		}
		int maxFlow = G.maxFlow(start, target);
		out.append(maxFlow+"\n");
		System.out.print(out);
	}
}
