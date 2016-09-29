package uva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _10938_Flea_circus {

	static class Edge {
		int to, l;

		public Edge(int to, int l) {
			super();
			this.to = to;
			this.l = l;
		}

	}

	static int T[], P[][], L[], nl, nodes;
	static long W[], D[][];

	static void process() {
		int i, j;
		// we initialize every element in P with -1
		for (i = 0; i < nodes; i++)
			for (j = 0; 1 << j < nodes; j++) {
				P[i][j] = -1;
				D[i][j] = 0;
			}
		// the first ancestor of every node i is T[i]
		for (i = 0; i < nodes; i++) {
			P[i][0] = T[i];
			D[i][0] = W[i];
		}
		// bottom up dynamic programming
		for (j = 1; 1 << j < nodes; j++)
			for (i = 0; i < nodes; i++)
				if (P[i][j - 1] != -1) {
					P[i][j] = P[P[i][j - 1]][j - 1];
					D[i][j] = D[i][j - 1] + D[P[i][j - 1]][j - 1];
				}
	}

	static long LCA(int p, int q) {
		int tmp, log, i;
		// if p is situated on a higher level than q then we swap them
		if (L[p] < L[q]) {
			tmp = p;
			p = q;
			q = tmp;
		}
		// we compute the value of [log(L[p)]
		for (log = 1; 1 << log <= L[p]; log++)
			; // ----
		log--;
		long sum = 0;
		// we find the ancestor of node p situated on the same level
		// with q using the values in P
		for (i = log; i >= 0; i--)
			if (L[p] - (1 << i) >= L[q]) {
				sum += D[p][i];
				p = P[p][i];
			}
		if (p == q)
			// [return p; LCA] [return max; MAX]
			return sum; // SUM

		// we compute LCA(p, q) using the values in P
		for (i = log; i >= 0; i--)
			if (P[p][i] != -1 && P[p][i] != P[q][i]) {
				sum += D[p][i] + D[q][i];
				p = P[p][i];
				q = P[q][i];
			}
		sum += D[p][0] + D[q][0];
		// [return T[p]; LCA] [return max; MAX]
		return sum; // SUM
	}

	public static void bfs(int root) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		v[root] = true;
		while (!q.isEmpty()) {
			int node = q.poll();
			for (int i = 0; i < G[node].size(); i++) {
				Edge nodde = G[node].get(i);
				int neigh = nodde.to;
				if (!v[neigh]) {
					v[neigh] = true;
					T[neigh] = node;
					W[neigh] = nodde.l;
					L[neigh] = L[node] + 1;
					q.add(neigh);
				}
			}
		}
	}

	public static ArrayList<Edge> G[];
	public static boolean[] v;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int theNodes = in.nextInt();
			if (theNodes == 0)
				break;
			nodes = 5010;
			v = new boolean[nodes];
			G = new ArrayList[nodes];
			for (int i = 0; i < nodes; i++)
				G[i] = new ArrayList<>();
			nl = (int) (Math.log(nodes) / Math.log(2)) + 1;
			T = new int[nodes]; // Parent after BFS
			P = new int[nodes][nl]; // Parents' i, dist 2^j LCA
			D = new long[nodes][nl]; // Parents' i, SUM 2^j
			L = new int[nodes];
			W = new long[nodes]; // SUM
			nodes = theNodes;
			for (int m = 0; m < nodes - 1; m++) {
				int v = in.nextInt() - 1, u = in.nextInt() - 1, dis = 1;
				G[u].add(new Edge(v, dis));
				G[v].add(new Edge(u, dis));
			}
			bfs(0);
			process();
			int queries = in.nextInt();

			for (int i = 0; i < queries; i++) {
				int v = in.nextInt() - 1, u = in.nextInt() - 1;
				long dis = LCA(u, v);
				long lcau = LCA(0, u), lcav = LCA(0, v);
				int mid = (int) ((LCA(u, v)) / 2);
				int run = 0;
				if (lcau > lcav)
					run = u;
				else
					run = v;
				for (int x = 1, y = 0; x <= mid && mid > 0; x <<= 1, y++)
					if ((x & mid) != 0) {
						run = P[run][y];
						mid -= x;
					}
				if (dis % 2L == 0L) {
					out.append("The fleas meet at " + ++run + ".\n");
				} else {
					out.append("The fleas jump forever between ");
					int a = run + 1, b = P[run][0] + 1;
					if (a > b) {
						int aux = a;
						a = b;
						b = aux;
					}
					out.append(a + " and " + b + ".\n");
				}
			}
		}
		System.out.print(out);
	}

}
