package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class _10075_Airlines {

	static class City {
		double lat, lon;

		public City(String d) {
			String[] w = d.trim().split(" ");
			lat = Math.toRadians(Double.parseDouble(w[1]));
			lon = Math.toRadians(Double.parseDouble(w[2]));
		}

	}

	public static int INF = Integer.MAX_VALUE / 2;
	public static long[][] dis;
	public static boolean vis[];
	public static HashMap<String, Integer> nameToId;
	public static City[] cities;
	public static int id, nodes;

	public static void init(int n) {
		nodes = n;
		dis = new long[nodes][nodes];
		vis = new boolean[nodes];
		nameToId = new HashMap<String, Integer>(nodes);
		cities = new City[nodes];
		id = 0;
		for (int i = 0; i < nodes; i++)
			for (int j = 0; j < nodes; j++)
				dis[i][j] = INF;
	}

	public static double h(double x) {
		return (1.0 - Math.cos(x)) / 2.0;
	}

	public static long distaceGeo(int u, int v) {
		City s = cities[u], t = cities[v];
		double a = h(t.lat - s.lat);
		double b = Math.cos(s.lat) * Math.cos(t.lat) * h(t.lon - s.lon);
		double c = 2 * Math.atan2(Math.sqrt(a + b), Math.sqrt(1 - a - b));
		return Math.round(6378 * c);
	}

	public static void union(int u, int v) {
		long d = distaceGeo(u, v);
		if (dis[u][v] > d) {
			dis[u][v] = d;
		}
	}

	public static void union(String u, String v) {
		if (!nameToId.containsKey(u))
			nameToId.put(u, id);
		if (!nameToId.containsKey(v))
			nameToId.put(v, id);
		union(nameToId.get(u), nameToId.get(v));
	}

	public static void floyd() {
		for (int i = 0; i < nodes; i++)
			dis[i][i] = 0;
		for (int k = 0; k < nodes; k++)
			for (int i = 0; i < nodes; i++)
				for (int j = 0; j < nodes; j++)
					if (dis[i][j] > dis[i][k] + dis[k][j])
						dis[i][j] = dis[i][k] + dis[k][j];

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] d = readInts(line);
			if (d[0] == 0 && d[1] == 0 && d[2] == 0)
				break;
			init(d[0]);
			for (int i = 0; i < d[0]; i++) {
				line = in.readLine().trim();
				String[] c = line.split(" ");
				if (!nameToId.containsKey(c[0])) {
					nameToId.put(c[0], id++);
				}
				cities[i] = new City(line);
			}
			for (int i = 0; i < d[1]; i++) {
				String[] c = in.readLine().trim().split(" ");
				union(c[0], c[1]);
			}
			floyd();
			if (nCase != 1)
				out.append("\n");
			out.append("Case #" + nCase++ + "\n");
			for (int i = 0; i < d[2]; i++) {
				String[] c = in.readLine().trim().split(" ");
				int s = nameToId.get(c[0]), t = nameToId.get(c[1]);
				if (dis[s][t] != INF)
					out.append(dis[s][t] + " km\n");
				else
					out.append("no route exists\n");
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
