package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class _10278_Fire_Station {

	public static HashSet<Integer> ss;

	static class Edge implements Comparable<Edge> {
		int id, w;

		public Edge(int id, int p) {
			this.id = id;
			this.w = p;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}

	}

	static class Dijkstra {

		int INF = Integer.MAX_VALUE / 2;
		ArrayList<Edge> adj[];
		int[] dis;
		boolean vis[];

		public Dijkstra(int maxNodes) {
			adj = new ArrayList[maxNodes];
			dis = new int[maxNodes];
			vis = new boolean[maxNodes];

		}

		public void init(int nodes) {
			for (int i = 0; i < nodes; i++) {
				adj[i] = new ArrayList<Edge>();
				dis[i] = INF;
				vis[i] = false;
			}
		}

		public void union(int u, int v, int w, boolean bidirectional) {
			adj[u].add(new Edge(v, w));
			if (bidirectional)
				adj[v].add(new Edge(u, w));
		}

		public void dijkstraFire(int s) {
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			q.add(new Edge(s, 0));
			dis[s] = 0;
			while (!q.isEmpty()) {
				Edge cur = q.poll();
				for (int i = 0; i < adj[cur.id].size(); i++) {
					Edge neigh = adj[cur.id].get(i);
					if (dis[cur.id] + neigh.w < dis[neigh.id]) {
						if (ss.contains(neigh.id))
							dis[neigh.id] = 0;
						else
							dis[neigh.id] = dis[cur.id] + neigh.w;
						q.add(new Edge(neigh.id, dis[neigh.id]));
					}
				}
			}
		}

		public int dijkstraTry(int s, int n) {
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			q.add(new Edge(s, 0));
			int[] d = new int[n];
			System.arraycopy(dis, 0, d, 0, n);
			d[s] = 0;
			while (!q.isEmpty()) {
				Edge cur = q.poll();
				for (int i = 0; i < adj[cur.id].size(); i++) {
					Edge neigh = adj[cur.id].get(i);
					if (d[cur.id] + neigh.w < d[neigh.id]) {
						if (ss.contains(neigh.id))
							d[neigh.id] = 0;
						else
							d[neigh.id] = d[cur.id] + neigh.w;
						q.add(new Edge(neigh.id, d[neigh.id]));
					}
				}
			}
			int max = 0;
			for (int i = 1; i < n; i++)
				max = Math.max(max, d[i]);
			return max;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int t = Integer.parseInt(in.readLine().trim());
		Dijkstra G = new Dijkstra(501);
		for (int tt = 0; tt < t; tt++) {
			if (tt == 0)
				in.readLine();
			int[] d = readInts(in.readLine());
			int stations = d[0], inter = d[1];
			G.init(inter + 1);
			int[] s = new int[1];
			ss = new HashSet<Integer>();
			if (stations > 0) {
				s = new int[stations];
				for (int i = 0; i < stations; i++) {
					s[i] = Integer.parseInt(in.readLine().trim());
					ss.add(s[i]);
				}
			}
			String link;
			while ((link = in.readLine()) != null && link.length() != 0) {
				d = readInts(link);
				G.union(d[0], d[1], d[2], true);
			}
			if(stations>0)
				G.dijkstraFire(s[0]);
			int min = 0, cur = 0, build = 1;
			for (int i = 1; i <= inter; i++)
				min = Math.max(min, G.dis[i]);
			for (int i = 1; i <= inter; i++) {
				if (!ss.contains(i)) {
					cur = G.dijkstraTry(i, inter + 1);
					if (min > cur) {
						min = cur;
						build = i;
					}
				}
			}
			if (tt != 0)
				out.append("\n");
			out.append(build + "\n");

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
