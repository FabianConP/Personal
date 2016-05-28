package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _12948_Interstellar_Travel {

	static final int INF = Integer.MAX_VALUE;

	static class State implements Comparable<State> {
		int to, c, s, t;

		public State(int to, int c, int s, int t) {
			this.to = to;
			this.c = c;
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(State o) {
			if (c == o.c)
				return t - o.t;
			return c - o.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		int nCase = 0;
		while ((line = in.readLine()) != null) {
			if (nCase++ != 0)
				out.append(".\n");
			int[] pfq = readInts(line);
			int p = pfq[0], f = pfq[1], q = pfq[2];
			HashMap<String, Integer> planets = new HashMap<>(p);
			ArrayList<Integer>[] G = new ArrayList[p];
			int[][] cost = new int[p][p];
			int[][] time = new int[p][p];
			int[][][] dist = new int[p][p][2];
			boolean[][] vis = new boolean[p][p];
			for (int i = 0; i < p; i++) {
				G[i] = new ArrayList<>();
				for (int j = 0; j < p; j++)
					cost[i][j] = time[i][j] = dist[i][j][0] = dist[i][j][1] = INF;
				cost[i][i] = time[i][i] = 0;
			}
			int idPlanet = 0;
			for (int i = 0; i < p; i++)
				planets.put(in.readLine().trim(), idPlanet++);
			int u, v, c, t;
			for (int i = 0; i < f; i++) {
				String[] info = in.readLine().trim().split(" ");
				u = planets.get(info[0]);
				v = planets.get(info[1]);
				c = Integer.parseInt(info[2]);
				t = Integer.parseInt(info[3]);
				if (cost[u][v] == INF)
					G[u].add(v);
				if (cost[u][v] > c || (cost[u][v]) == c && time[u][v] > t) {
					cost[u][v] = c;
					time[u][v] = t;
				}
			}
			int idStart = planets.get(in.readLine().trim()), idTo, stops;
			PriorityQueue<State> pq = new PriorityQueue<>();
			State x = new State(idStart, 0, 0, 0);
			pq.add(x);
			dist[idStart][0][0] = dist[idStart][0][1] = 0;
			while (!pq.isEmpty()) {
				x = pq.poll();
				if (vis[x.to][x.s])
					continue;
				vis[x.to][x.s] = true;
				for (int i = 0; i < G[x.to].size(); i++) {
					int neigh = G[x.to].get(i);
					if (x.s + 1 < p && x.c + cost[x.to][neigh] < dist[neigh][x.s + 1][0] && !vis[neigh][x.s + 1]) {
						dist[neigh][x.s + 1][0] = x.c + cost[x.to][neigh];
						dist[neigh][x.s + 1][1] = x.t + time[x.to][neigh];
						pq.add(new State(neigh, dist[neigh][x.s + 1][0], x.s + 1, dist[neigh][x.s + 1][1]));
					}
				}
			}
			for (int i = 0; i < p; i++)
				for (int j = 1; j < p; j++)
					if (dist[i][j - 1][0] < dist[i][j][0]) {
						dist[i][j][0] = dist[i][j - 1][0];
						dist[i][j][1] = dist[i][j - 1][1];
					}
			for (int i = 0; i < q; i++) {
				String[] info = in.readLine().trim().split(" ");
				idTo = planets.get(info[0]);
				stops = Integer.parseInt(info[1]) + 1;
				stops = Math.min(stops, p - 1);
				if (dist[idTo][stops][0] == INF)
					out.append("* *\n");
				else
					out.append(dist[idTo][stops][0] + " " + dist[idTo][stops][1] + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
	