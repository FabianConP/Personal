package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class _10986_Sending_email {

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
	int[] dis, parent;
	boolean vis[];

	public Dijkstra(int maxNodes) {
		adj = new ArrayList[maxNodes];
		dis = new int[maxNodes];
		parent = new int[maxNodes];
		vis = new boolean[maxNodes];

	}

	public void init(int nodes) {
		for (int i = 0; i < nodes; i++) {
			adj[i] = new ArrayList<Edge>();
			dis[i] = INF;
			parent[i] = -1;
			vis[i] = false;
		}
	}

	public void union(int u, int v, int w, boolean bidirectional) {
		adj[u].add(new Edge(v, w));
		if (bidirectional)
			adj[v].add(new Edge(u, w));
	}

	public void dijkstra(int s) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(s, 0));
		dis[s] = 0;
		while (!q.isEmpty()) {
			Edge cur = q.poll();
			if (vis[cur.id])
				continue;
			vis[s] = true;
			for (int i = 0; i < adj[cur.id].size(); i++) {
				Edge neigh = adj[cur.id].get(i);
				if (!vis[neigh.id]) {
					if (dis[cur.id] + neigh.w < dis[neigh.id]) {
						dis[neigh.id] = dis[cur.id] + neigh.w;
						parent[neigh.id] = cur.id;
						q.add(new Edge(neigh.id, dis[neigh.id]));
					}
				}
			}
		}
	}
	
	public ArrayList<Integer> getPath(int t){
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(t);
		while(parent[t]!=-1){
			path.add(parent[t]);
			t = parent[t];
		}
		Collections.reverse(path);
		return path;
	}
}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		Dijkstra G = new Dijkstra(20000);
		int nTest = Integer.parseInt(in.readLine().trim());
		for (int tt = 0; tt < nTest; tt++) {
			int[] v = readInts(in.readLine());
			int n = v[0], m = v[1], s = v[2], t = v[3];
			G.init(n);
			for (int i = 0; i < m; i++) {
				int[] abw = readInts(in.readLine());
				G.union(abw[0], abw[1], abw[2], true);
			}
			G.dijkstra(s);
			out.append("Case #" + (tt + 1) + ": ");
			if (G.dis[t] == G.INF)
				out.append("unreachable\n");
			else
				out.append(G.dis[t] + "\n");
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
