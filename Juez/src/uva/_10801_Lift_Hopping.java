package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class _10801_Lift_Hopping {
	static class Edge implements Comparable<Edge> {
		int id, w, e;

		public Edge(int id, int w, int e) {
			this.id = id;
			this.w = w;
			this.e = e;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", w=" + w + ", e=" + e + "]";
		}

	}

	static class Dijkstra {

		int INF = Integer.MAX_VALUE / 2;
		ArrayList<Edge> adj[];
		int[] dis, cost;
		boolean vis[];

		public Dijkstra(int maxNodes) {
			adj = new ArrayList[maxNodes];
			dis = new int[maxNodes];
			vis = new boolean[maxNodes];
			cost = new int[maxNodes];
		}

		public void init(int nodes) {
			for (int i = 0; i < nodes; i++) {
				adj[i] = new ArrayList<Edge>();
				dis[i] = INF;
				vis[i] = false;
				cost[i] = INF;
			}
		}

		public void union(int u, int v, int w, int e, boolean bidirectional) {
			adj[u].add(new Edge(v, w, e));
			if (bidirectional)
				adj[v].add(new Edge(u, w, e));
		}

		public int dijkstra(int s, int t) {
			for (int i = 0; i < cost.length; i++) {
				dis[i] = INF;
				vis[i] = false;
			}
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			q.add(new Edge(s, 0, -1));
			dis[s] = 0;
			while (!q.isEmpty()) {
				Edge u = q.poll();
				if (u.id == t)
					return u.w;
				for (int j = 0; j < adj[u.id].size(); j++) {
					Edge v = adj[u.id].get(j);
					int w = v.w + ((u.e != v.e && u.e != -1) ? 60 : 0);
					if (u.w + w < dis[v.id]) {
						dis[v.id] = u.w + w;
						q.add(new Edge(v.id, dis[v.id], v.e));
					}
				}
			}
			return INF;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		Dijkstra G = new Dijkstra(101);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nk = readInts(line);
			int[] cost = readInts(in.readLine());
			G.init(101);
			for (int i = 0; i < cost.length; i++)
				G.cost[i] = cost[i];
			int[][] s = new int[nk[0]][];
			boolean[][] ss = new boolean[nk[0]][101];
			for (int i = 0; i < nk[0]; i++) {
				s[i] = readInts(in.readLine());
				for (int a = 0; a < s[i].length; a++) {
					ss[i][s[i][a]] = true;
					for (int b = a + 1; b < s[i].length; b++)
						G.union(s[i][a], s[i][b],
								cost[i] * Math.abs(s[i][b] - s[i][a]), i, true);
				}
			}
			for (int i = 0; i < s.length; i++) {
				for (int a = 0; a < s[i].length; a++)
					for (int j = i + 1; j < s.length; j++) {
						if (ss[j][s[i][a]])
							for (int b = 0; b < s[j].length; b++)
								G.union(s[i][a],
										s[j][b],
										(cost[j] * Math.abs(s[j][b] - s[i][a])) + 60,
										j, true);
					}
			}
			if (G.dijkstra(0, nk[1]) == G.INF)
				out.append("IMPOSSIBLE\n");
			else
				out.append(G.dis[nk[1]] + "\n");
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
