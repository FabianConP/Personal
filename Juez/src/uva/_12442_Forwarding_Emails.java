package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _12442_Forwarding_Emails {
	private static class Tarjan {

		ArrayList<Integer>[] ady;
		boolean[] visited;
		int[] dfs_num, dfs_low;
		Stack<Integer> s;
		int dfsNumberCounter, numSCC;
		ArrayList<ArrayList<Integer>> res;

		public Tarjan(int V) {
			ady = new ArrayList[V];
			visited = new boolean[V];
			dfs_num = new int[V];
			dfs_low = new int[V];
			for (int i = 0; i < V; i++) {
				ady[i] = new ArrayList<Integer>();
				dfs_num[i] = -1;
			}
			s = new Stack<Integer>();
			dfsNumberCounter = numSCC = 0;
		}

		public void add(int u, int v) {
			ady[u].add(v);
		}

		public void tarjanSCC(int u) {
			dfs_num[u] = dfs_low[u] = dfsNumberCounter++;
			s.push(u);
			visited[u] = true;
			for (int i = 0; i < ady[u].size(); i++) {
				int v = ady[u].get(i);
				if (dfs_num[v] == -1)
					tarjanSCC(v);
				if (visited[v])
					dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			if (dfs_low[u] == dfs_num[u]) {
				res.add(new ArrayList<Integer>());
				int v;
				do {
					v = s.pop();
					res.get(numSCC).add(v);
					visited[v] = false;
				} while (u != v);
				numSCC++;
			}
		}

		public ArrayList<ArrayList<Integer>> SCC() {
			res = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < ady.length; i++)
				if (dfs_num[i] == -1)
					tarjanSCC(i);
			return res;
		}
	}

	static ArrayList<Integer>[] G;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < nCases; t++) {
			int n = Integer.parseInt(in.readLine().trim());
			Tarjan T = new Tarjan(n);
			for (int i = 0; i < n; i++) {
				int[] uv = readInts(in.readLine());
				int u = uv[0] - 1, v = uv[1] - 1;
				T.add(u, v);
			}
			ArrayList<ArrayList<Integer>> SCC = T.SCC();
			n = SCC.size();
			G = new ArrayList[n];
			for (int i = 0; i < n; i++)
				G[i] = new ArrayList<>();
			int[] inn = new int[n];
			int[] id = new int[T.ady.length];
			int[] w = new int[n];
			int[] bestId = new int[n];
			int max = 0, cur, indexMax = 0;
			for (int i = 0; i < SCC.size(); i++) {
				ArrayList<Integer> S = SCC.get(i);
				bestId[i] = Integer.MAX_VALUE;
				for (int j = 0; j < S.size(); j++) {
					cur = S.get(j);
					id[cur] = i;
					bestId[i] = Math.min(bestId[i], cur);
				}
				w[i] = S.size();
			}
			int u, v;
			for (int i = 0; i < T.ady.length; i++) {
				u = i;
				for (int j = 0; j < T.ady[i].size(); j++) {
					v = T.ady[i].get(j);
					if (id[u] != id[v]) {
						G[id[u]].add(id[v]);
						inn[id[v]]++;
					}
				}
			}
			int[] depth = new int[n];
			int[] idDepth = new int[n];
			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < n; i++)
				if (inn[i] == 0) {
					q.add(i);
					idDepth[i] = i;
				}
			while (!q.isEmpty()) {
				u = q.poll();
				depth[u] += w[u];
				if (depth[u] > max || (depth[u] == max && bestId[idDepth[u]] < indexMax)) {
					max = depth[u];
					indexMax = bestId[idDepth[u]];
				}
				for (int i = 0; i < G[u].size(); i++) {
					v = G[u].get(i);
					if (--inn[v] == 0)
						q.add(v);
					if (depth[u] > depth[v] || (depth[u] == depth[v] && bestId[idDepth[u]] < bestId[idDepth[v]])) {
						depth[v] = depth[u];
						idDepth[v] = idDepth[u];
					}
				}
			}

			int best = indexMax;
			out.append("Case " + (t + 1) + ": " + (best + 1) + "\n");
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
