package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class _10449_Traffic {
	public static class Edge {
		int v, p;

		public Edge(int v, int p) {
			this.p = p;
			this.v = v;
		}
	}

	public static ArrayList<Edge> adj[];
	public static int cost[], dist[];
	public static boolean visited[], cycle[];
	public static int INF = Integer.MAX_VALUE / 2;
	public static int V;

	public static void init(int maxTam) {
		V = maxTam;
		adj = new ArrayList[V];
		dist = new int[V];
		cost = new int[V];
		visited = new boolean[V];
		cycle = new boolean[V];
	}

	public static void clean() {
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Edge>();
			dist[i] = INF;
			cost[i] = 0;
			visited[i] = false;
			cycle[i] = false;
		}
	}

	public static void bellmanFord(int s) {
		dist[s] = 0;

		for (int k = 0; k < V - 1; k++)
			for (int i = 0; i < V; i++)
				for (int j = 0; j < adj[i].size(); j++) {
					Edge neigh = adj[i].get(j);
					int v = neigh.v, p = neigh.p;
					if (dist[i] != INF && dist[i] + p < dist[v])
						dist[v] = dist[i] + p;
				}
		for (int u = 0; u < V; u++)
			for (int j = 0; j < adj[u].size(); j++) {
				Edge neigh = adj[u].get(j);
				int v = neigh.v, p = neigh.p;
				if (dist[u] != INF && dist[u] + p < dist[v]) {
					dist[v] = dist[u] + p;
					cycle[v] = true;
				}
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		init(201);
		int nSet = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] data;
			try {
				data = readInts(line);
			} catch (Exception e) {
				break;
			}
			int n = data[0];
			clean();
			for (int i = 0; i < n; i++)
				cost[i] = data[i + 1];
			int edges = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < edges; i++) {
				int[] uv = readInts(in.readLine());
				int u = uv[0] - 1, v = uv[1] - 1;
				adj[u].add(new Edge(v, (int) Math.pow(cost[v] - cost[u], 3)));
			}
			out.append("Set #" + nSet++ + "\n");
			bellmanFord(0);
			int q = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < q; i++) {
				int t = Integer.parseInt(in.readLine().trim()) - 1;
				if (dist[t] < 3 || dist[t] == INF || cycle[t])
					out.append("?\n");
				else
					out.append(dist[t] + "\n");
			}
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
