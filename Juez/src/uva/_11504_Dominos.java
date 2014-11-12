package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class _11504_Dominos {

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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCase = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < nCase; i++) {
			String[] info = in.readLine().trim().split(" ");
			int n = Integer.parseInt(info[0]);
			int m = Integer.parseInt(info[1]);
			Tarjan t = new Tarjan(n);
			for (int j = 0; j < m; j++) {
				String[] edge = in.readLine().split(" ");
				int a = Integer.parseInt(edge[0]);
				int b = Integer.parseInt(edge[1]);
				t.add(a - 1, b - 1);
			}
			ArrayList<ArrayList<Integer>> res = t.SCC();
			boolean[] used = new boolean[res.size()];
			int[] toCicle = new int[n];
			Stack<Integer> s = new Stack<Integer>();
			for (int j = 0; j < res.size(); j++) {
				s.push(j);
				ArrayList<Integer> cicle = res.get(j);
				for (int k = 0; k < cicle.size(); k++)
					toCicle[cicle.get(k)] = j;
			}
			while (!s.isEmpty()) {
				int idCicle = s.pop();
				ArrayList<Integer> cicle = res.get(idCicle);
				for (int j = 0; j < cicle.size(); j++) {
					int node = cicle.get(j);
					for (int k = 0; k < t.ady[node].size(); k++) {
						int neigh = t.ady[node].get(k);
						if (!used[toCicle[neigh]]
								&& toCicle[neigh] != toCicle[node]) {
							used[toCicle[neigh]] = true;
						}
					}
				}
			}
			int need = 0;
			for (int j = 0; j < used.length; j++)
				if (!used[j])
					need++;
			out.append(need + "\n");
		}
		System.out.print(out);
	}
}