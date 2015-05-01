package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class _186_Trip_Routing {

	static class Route {
		String from, to, route;
		int miles;

		public Route(String from, String to, String route, int miles) {
			this.from = from;
			this.to = to;
			this.route = route;
			this.miles = miles;
		}

		@Override
		public String toString() {
			return f(from, 21) + f(to, 21) + f(route, 11)
					+ String.format("% 5d", miles);
		}

		private String f(String s, int l) {
			String r = "";
			for (int i = 0; i < l - s.length(); i++)
				r += " ";
			return s + r;
		}
	}

	public static int INF = Integer.MAX_VALUE / 2;
	public static int[][] dis, parent;
	public static boolean vis[];
	public static HashMap<String, Integer> nameToId;
	public static String[] idToName;
	public static int id, nodes;
	public static String[][] routes;

	public static void init(int n) {
		nodes = n;
		dis = new int[nodes][nodes];
		parent = new int[nodes][nodes];
		vis = new boolean[nodes];
		nameToId = new HashMap<String, Integer>(nodes);
		idToName = new String[nodes];
		routes = new String[nodes][nodes];
		id = 0;
		for (int i = 0; i < nodes; i++)
			for (int j = 0; j < nodes; j++)
				dis[i][j] = INF;
	}

	public static void union(int u, int v, int w, String r) {
		if (dis[u][v] != -INF && dis[u][v] > w) {
			dis[u][v] = w;
			routes[u][v] = r;
			dis[v][u] = w;
			routes[v][u] = r;
		}
	}

	public static void union(String u, String v, int w, String r) {
		if (!nameToId.containsKey(u)) {
			nameToId.put(u, id);
			idToName[id++] = u;
		}
		if (!nameToId.containsKey(v)) {
			nameToId.put(v, id);
			idToName[id++] = v;
		}
		union(nameToId.get(u), nameToId.get(v), w, r);
	}

	public static void floyd() {
		for (int i = 0; i < nodes; i++) {
			dis[i][i] = 0;
			for (int j = 0; j < nodes; j++) {
				parent[i][j] = i;
				if (dis[i][j] == INF || i == j) {
					parent[i][j] = -1;
				}
			}
		}
		for (int k = 0; k < nodes; k++)
			for (int i = 0; i < nodes; i++)
				for (int j = 0; j < nodes; j++) 
					if (dis[i][j] > dis[i][k] + dis[k][j]) {
						dis[i][j] = dis[i][k] + dis[k][j];
						parent[i][j] = parent[k][j];
					}
	}

	public static ArrayList<Route> getPath(int s, int t) {
		ArrayList<Route> path = new ArrayList<Route>();
		int c;
		while (parent[s][t] != -1) {
			c = parent[s][t];
			path.add(new Route(idToName[c], idToName[t], routes[c][t],
					dis[c][t]));
			t = c;
		}
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		init(101);
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] d = line.trim().split(",");
			union(d[0], d[1], Integer.parseInt(d[3]), d[2]);
		}
		floyd();
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] d = line.trim().split(",");
			int s = nameToId.get(d[0]), t = nameToId.get(d[1]);
			ArrayList<Route> path = getPath(s, t);
			out.append("\n\nFrom                 To                   Route      Miles\n");
			out.append("-------------------- -------------------- ---------- -----\n");
			for (Route route : path)
				out.append(route + "\n");
			out.append("                                                     -----\n");
			out.append("                                          Total      ");
			out.append(String.format("% 5d", dis[s][t]) + "\n");
		}
		System.out.print(out);
	}
}
