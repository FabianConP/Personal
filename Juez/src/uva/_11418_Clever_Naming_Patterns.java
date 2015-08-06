package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class _11418_Clever_Naming_Patterns {
	
	static class Edge {
		int t, rev, cap, f;

		public Edge(int t, int rev, int cap) {
			this.t = t;// Target
			this.rev = rev; // Reverse node
			this.cap = cap; // Capacity
		}

		@Override
		public String toString() {
			return "Edge [t=" + t + ", rev=" + rev + ", cap=" + cap
					+ ", f=" + f + "]";
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
	public static int P = 1; // Problems
	public static int N = 53; // Names

	public static HashMap<String, Integer> mapNames;
	public static HashMap<Integer, String> mapNames2;
	public static ArrayList<String> listNames[];

	public static int start, target;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < nCase; i++) {
			int nProblems = Integer.parseInt(in.readLine().trim());
			mapNames = new HashMap<String, Integer>();
			mapNames2 = new HashMap<Integer, String>();
			listNames = new ArrayList[nProblems];
			int numNames = 0;
			for (int p = 0; p < nProblems; p++) {
				listNames[p] = new ArrayList<String>();
				String[] names = in.readLine().trim().split(" ");
				for (int k = 1; k < names.length; k++) {
					mapNames.put(names[k], numNames);
					mapNames2.put(numNames++, names[k]);
					listNames[p].add(names[k]);
				}
			}
			start = 0;
			target = 2 + nProblems + numNames + 26;
			L = 1 + nProblems;
			N = L + 26;
			Dinic G = new Dinic(target + 1);
			for (int p = 0; p < nProblems; p++)
				G.addEdge(start, P + p, 1);
			for (int p = 0; p < nProblems; p++) {
				ArrayList<String> names = listNames[p];
				for (int j = 0; j < names.size(); j++) {
					String name = names.get(j);
					int idName = mapNames.get(name);
					int first = Character.toLowerCase(name.charAt(0)) - 'a';
					G.addEdge(P + p, N + idName, 1);
					G.addEdge(N + idName, L + first, 1);
				}
			}
			for (int l = 0; l < nProblems; l++)
				G.addEdge(L + l, target, 1);
			int maxFlow = G.maxFlow(start, target);
			out.append("Case #" + (i + 1) + ":\n");
			ArrayList<String> ans = new ArrayList<String>(nProblems);
			for (int p = 0; p < nProblems; p++) {
				String name = "";
				for (int j = 0; j < G.ady[P + p].size(); j++) {
					Edge e = G.ady[P + p].get(j);
					if (e.t != start && e.f != 0) {
						name = mapNames2.get(e.t - N);
						break;
					}
				}
				ans.add(capitalized(name));
			}
			Collections.sort(ans);
			for (String name : ans)
				out.append(name + "\n");
		}
		System.out.print(out);
	}

	public static String capitalized(String word) {
		char[] w = word.toCharArray();
		String cap = Character.toUpperCase(w[0]) + "";
		for (int i = 1; i < w.length; i++)
			cap += Character.toLowerCase(w[i]);
		return cap;
	}
}
