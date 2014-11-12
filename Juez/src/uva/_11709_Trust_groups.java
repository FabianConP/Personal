package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class _11709_Trust_groups {
	
	private static class Tarjan {
		ArrayList<Integer>[] ady;
		boolean[] visited;
		int[] dfs_num, dfs_low;
		Stack<Integer> s;
		int dfsNumberCounter, numSCC;

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
				int v;
				do {
					v = s.pop();
					visited[v] = false;
				} while (u != v);
				numSCC++;
			}
		}

		public void SCC() {
			for (int i = 0; i < ady.length; i++)
				if (dfs_num[i] == -1)
					tarjanSCC(i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] info = line.trim().split(" ");
			int n = Integer.parseInt(info[0]);
			int m = Integer.parseInt(info[1]);
			if (n == 0 && m == 0)
				break;
			Tarjan t = new Tarjan(n);
			HashMap<String, Integer> names = new HashMap<>();
			int id = 0;
			for (int j = 0; j < n; j++) {
				String name = in.readLine().trim();
				if (!names.containsKey(name))
					names.put(name, id++);
			}
			for (int j = 0; j < m; j++) {
				int a = names.get(in.readLine().trim());
				int b = names.get(in.readLine().trim());
				t.add(a, b);
			}
			t.SCC();
			out.append(t.numSCC + "\n");
		}
		System.out.print(out);
	}
}
