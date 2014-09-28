package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11690_Money_Matters {

	public static int[] owe, total;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int[] nm = readInts(in.readLine());
				int n = nm[0];
				int m = nm[1];
				WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(n + 1);
				owe = new int[n + 1];
				total = new int[n + 1];
				for (int j = 0; j < n; j++)
					owe[j] = Integer.parseInt(in.readLine().trim());
				int a, b;
				for (int j = 0; j < m; j++) {
					int[] ab = readInts(in.readLine());
					a = ab[0];
					b = ab[1];
					wqu.union(a, b);
				}
				for (int j = 0; j <= n; j++)
					total[wqu.find(j)] += owe[j];
				boolean pos = true;
				for (int j = 0; j < total.length; j++)
					if (total[j] != 0) {
						pos = false;
						break;
					}
				out.append((!pos ? "IM" : "") + "POSSIBLE\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

	private static class WeightedQuickUnionUF {
		private int[] id; // id[i] = parent of i
		private int[] sz; // sz[i] = number of objects in subtree rooted at i
		private int count; // number of components

		/**
		 * Initializes an empty union-find data structure with N isolated
		 * components 0 through N-1.
		 * 
		 * @throws java.lang.IllegalArgumentException
		 *             if N < 0
		 * @param N
		 *            the number of objects
		 */
		public WeightedQuickUnionUF(int N) {
			count = N;
			id = new int[N];
			sz = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i] = 1;
			}
		}

		/**
		 * Returns the number of components.
		 * 
		 * @return the number of components (between 1 and N)
		 */
		public int count() {
			return count;
		}

		/**
		 * Returns the component identifier for the component containing site
		 * <tt>p</tt>.
		 * 
		 * @param p
		 *            the integer representing one site
		 * @return the component identifier for the component containing site
		 *         <tt>p</tt>
		 * @throws java.lang.IndexOutOfBoundsException
		 *             unless 0 <= p < N
		 */
		public int find(int p) {
			while (p != id[p])
				p = id[p];
			return p;
		}

		/**
		 * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
		 * 
		 * @param p
		 *            the integer representing one site
		 * @param q
		 *            the integer representing the other site
		 * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are
		 *         in the same component, and <tt>false</tt> otherwise
		 * @throws java.lang.IndexOutOfBoundsException
		 *             unless both 0 <= p < N and 0 <= q < N
		 */
		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		/**
		 * Merges the component containing site<tt>p</tt> with the component
		 * containing site <tt>q</tt>.
		 * 
		 * @param p
		 *            the integer representing one site
		 * @param q
		 *            the integer representing the other site
		 * @throws java.lang.IndexOutOfBoundsException
		 *             unless both 0 <= p < N and 0 <= q < N
		 */
		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ)
				return;

			// make smaller root point to larger one
			if (sz[rootP] < sz[rootQ]) {
				id[rootP] = rootQ;
				sz[rootQ] += sz[rootP];
			} else {
				id[rootQ] = rootP;
				sz[rootP] += sz[rootQ];
			}
			count--;
		}

	}
}
