package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class _11367_Full_Tank {

	static class Edge implements Comparable<Edge> {

		int id, w, g;

		public Edge(int id, int w, int g) {
			this.id = id;
			this.w = w;
			this.g = g;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", w=" + w + ", g=" + g + "]";
		}

	}

	static class Dijkstra {

		int INF = Integer.MAX_VALUE / 2;
		ArrayList<Edge> adj[];
		int[] d[], cost;

		public Dijkstra(int maxNodes) {
			adj = new ArrayList[maxNodes];
			d = new int[maxNodes][101];
			cost = new int[maxNodes];
		}

		public void init(int nodes) {
			for (int i = 0; i < nodes; i++) {
				adj[i] = new ArrayList<Edge>();
				cost[i] = INF;
			}
		}

		public void union(int u, int v, int w, boolean bidirectional) {
			adj[u].add(new Edge(v, w, 0));
			if (bidirectional)
				adj[v].add(new Edge(u, w, 0));
		}

		public int dijkstra(int s, int t, int c) {
			for (int i = 0; i < cost.length; i++)
				Arrays.fill(d[i], INF);
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			q.add(new Edge(s, 0, 0));
			d[s][0] = 0;
			while (!q.isEmpty()) {
				Edge u = q.poll();
				if(u.id == t)
					return u.w;
				if (d[u.id][u.g] < u.w)
					continue;
				if (u.g < c && d[u.id][u.g + 1] > u.w + cost[u.id]) {
					d[u.id][u.g + 1] = u.w + cost[u.id];
					q.add(new Edge(u.id, d[u.id][u.g + 1], u.g + 1));
				}
				for (int i = 0; i < adj[u.id].size(); i++) {
					Edge v = adj[u.id].get(i);
					if (u.g >= v.w && d[v.id][u.g - v.w] > u.w) {
						d[v.id][u.g - v.w] = u.w;
						q.add(new Edge(v.id, d[v.id][u.g - v.w], u.g - v.w));
					}
				}
			}
			return INF;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int[] nm = readInts(in.readLine());
		int n = nm[0], m = nm[1];
		Dijkstra G = new Dijkstra(n + 1);
		G.init(n + 1);
		int[] cost = readInts(in.readLine());
		for (int i = 0; i < cost.length; i++)
			G.cost[i] = cost[i];
		for (int i = 0; i < m; i++) {
			int[] uvl = readInts(in.readLine());
			int u = uvl[0], v = uvl[1], l = uvl[2];
			G.union(u, v, l, true);
		}
		int queries = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < queries; i++) {
			int[] cuv = readInts(in.readLine());
			int u = cuv[1], v = cuv[2], c = cuv[0];
			int ans = G.dijkstra(u, v, c);
			if (ans == G.INF)
				out.append("impossible\n");
			else
				out.append(ans + "\n");
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
