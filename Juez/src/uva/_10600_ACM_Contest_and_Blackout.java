package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _10600_ACM_Contest_and_Blackout {
	static final int MAX = 105;
	static final int MAXNL = (int) (Math.log(MAX) / Math.log(2)) + 1;
	static int padre[] = new int[MAX];
	static int rango[] = new int[MAX];

	static void MakeSet(int n) {
		for (int i = 0; i < n; i++) {
			padre[i] = i;
			rango[i] = 0;
		}

	}

	static int Find(int x) {
		return (x == padre[x]) ? x : (padre[x] = Find(padre[x]));
	}

	static void unionByRank(int x, int y) {
		int xRoot = Find(x);
		int yRoot = Find(y);
		if (rango[xRoot] > rango[yRoot]) {
			padre[yRoot] = xRoot;
		} else {
			padre[xRoot] = yRoot;
			if (rango[xRoot] == rango[yRoot]) {
				rango[yRoot]++;
			}
		}

	}

	static boolean sameComponent(int x, int y) {
		if (Find(x) == Find(y))
			return true;
		return false;
	}
	// /FIN UNION-FIND

	static int V, E;

	static class Edge implements Comparable<Edge> {
		int origen;
		int destino;
		int peso;

		public Edge(int origen, int destino, int peso) {
			this.origen = origen;
			this.destino = destino;
			this.peso = peso;
		}

		@Override
		public int compareTo(Edge o) {
			return peso - o.peso;
		}

		public String toString() {
			return "origen : " + origen + " destino : " + destino + " peso : " + peso;
		}
	}

	static Edge arista[] = new Edge[MAX * MAX];
	static Edge[] MST = new Edge[MAX];
	static boolean[] used = new boolean[MAX * MAX];

	static int KruskalMST() {
		int origen, destino, peso;
		int total = 0;
		int numAristas = 0;

		Arrays.fill(used, 0, V * V, false);
		MakeSet(V);

		for (int i = 0; i < E; ++i) {
			origen = arista[i].origen;
			destino = arista[i].destino;
			peso = arista[i].peso;
			if (!sameComponent(origen, destino)) {
				total += peso;
				MST[numAristas] = arista[i];
				used[i] = true;
				unionByRank(origen, destino);
				numAristas++;
			}
		}
		return numAristas != V - 1 ? Integer.MAX_VALUE : total;
	}

	static long max(long a, long b, long c) {
		return Math.max(a, Math.max(b, c));
	}

	public static ArrayList<Edge> G[] = new ArrayList[MAX];
	public static boolean[] v = new boolean[MAX];
	public static int T[] = new int[MAX];
	public static int P[][] = new int[MAX][MAXNL];
	public static int L[] = new int[MAX];;
	static long H[] = new long[MAX];
	static long M[][] = new long[MAX][MAXNL];
	public static int nl, nodes;

	static void process() {
		int i, j;

		// we initialize every element in P with -1
		for (i = 0; i < nodes; i++)
			for (j = 0; 1 << j < nodes; j++) {
				P[i][j] = -1;
				M[i][j] = 0;
			}

		// the first ancestor of every node i is T[i]
		for (i = 0; i < nodes; i++) {
			P[i][0] = T[i];
			M[i][0] = H[i];
		}

		// bottom up dynamic programming
		for (j = 1; 1 << j < nodes; j++)
			for (i = 0; i < nodes; i++)
				if (P[i][j - 1] != -1) {
					P[i][j] = P[P[i][j - 1]][j - 1];
					M[i][j] = Math.max(M[i][j - 1], M[P[i][j - 1]][j - 1]);
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
			;
		log--;

		long max = 0;

		// we find the ancestor of node p situated on the same level
		// with q using the values in P
		for (i = log; i >= 0; i--)
			if (L[p] - (1 << i) >= L[q]) {
				max = Math.max(max, M[p][i]);
				p = P[p][i];
			}

		if (p == q)
			return max;

		// we compute LCA(p, q) using the values in P
		for (i = log; i >= 0; i--)
			if (P[p][i] != -1 && P[p][i] != P[q][i]) {
				max = max(max, M[p][i], M[q][i]);
				p = P[p][i];
				q = P[q][i];
			}
		max = max(max, M[p][0], M[q][0]);
		return max;
	}

	public static void bfs(int root) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(v, 0, V, false);
		q.add(root);
		v[root] = true;
		while (!q.isEmpty()) {
			int node = q.poll();
			for (int i = 0; i < G[node].size(); i++) {
				Edge nodde = G[node].get(i);
				int neigh = nodde.destino;
				if (!v[neigh]) {
					v[neigh] = true;
					T[neigh] = node;
					H[neigh] = nodde.peso;
					L[neigh] = L[node] + 1;
					q.add(neigh);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int testCases = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < testCases; t++) {
			int[] nm = readInts(in.readLine());
			int n = nm[0], m = nm[1];
			for (int i = 0; i < m; i++) {
				int[] uvw = readInts(in.readLine());
				int u = uvw[0] - 1, v = uvw[1] - 1, w = uvw[2];
				if (u > v) {
					int aux = u;
					u = v;
					v = aux;
				}
				arista[i] = new Edge(u, v, w);
			}
			E = m;
			V = n;
			Arrays.sort(arista, 0, E);
			long a = KruskalMST(), b = Long.MAX_VALUE;
			nodes = n;
			nl = (int) (Math.log(nodes) / Math.log(2)) + 1;
			for (int i = 0; i < n; i++)
				G[i] = new ArrayList<>();
			for (int i = 0; i < V - 1; i++) {
				G[MST[i].origen].add(new Edge(MST[i].origen, MST[i].destino, MST[i].peso));
				G[MST[i].destino].add(new Edge(MST[i].destino, MST[i].origen, MST[i].peso));
			}
			bfs(0);
			process();
			for (int i = 0; i < E; i++)
				if (!used[i])
					b = Math.min(b, a + arista[i].peso - LCA(arista[i].origen, arista[i].destino));
			if (b == Long.MAX_VALUE)
				b = a;
			out.append(a + " " + b + "\n");
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
