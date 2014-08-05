package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class _760_DNA_Sequencing {

	static void print(Object... obj) {
		for (int i = 0; i < obj.length; i++) {

			System.out.print(obj[i]);
		}
		System.out.println();
	}

	public static class SuffixArrayClass {

		public static char cad[];// max 100010
		public Node sA[];
		public int n;
		public static int lcp[];

		public static class Node implements Comparable<Node> {
			int index;
			int rank;
			int nextRank;

			public Node(int index, int rank) {
				this.index = index;
				this.rank = rank;
				this.nextRank = ((index == cad.length - 1) ? -300
						: cad[index + 1]);

			}

			@Override
			public int compareTo(Node o) {
				return (rank == o.rank) ? nextRank - o.nextRank : rank - o.rank;
			}

			@Override
			public String toString() {
				String c = "";
				for (int i = index; i < cad.length; i++) {
					c += cad[i];
				}
				return index + " " + c + "\n";
			}
		}

		public void init(char cad[]) {
			this.cad = cad;
			this.n = cad.length;
			sA = new Node[n];
		}

		public void construct() {
			for (int i = 0; i < n; i++) {
				sA[i] = new Node(i, cad[i]);
			}
			int prev;
			int rank = 0;

			Arrays.sort(sA);

			int ind[] = new int[n];
			for (int i = 2; i <= 2 * n; i <<= 1) {

				rank = 0;
				prev = sA[0].rank;
				sA[0].rank = 0;

				for (int j = 1; j < n; j++) {

					if (sA[j].rank == prev
							&& sA[j].nextRank == sA[j - 1].nextRank) {
						prev = sA[j].rank;
						sA[j].rank = rank;
					} else {
						prev = sA[j].rank;
						sA[j].rank = ++rank;
					}
					ind[sA[j].index] = j;
				}

				for (int j = 0; j < n; j++) {

					if (sA[j].index + i < n) {
						sA[j].nextRank = sA[ind[sA[j].index + i]].rank;
					} else {
						sA[j].nextRank = -1;// cuando al doblar no hay rank
					}
				}

				Arrays.sort(sA);

			}

		}

		public int[] lpc() {
			int phi[] = new int[n];
			int plcp[] = new int[n];
			lcp = new int[n];
			phi[sA[0].index] = -1;

			for (int i = 1; i < n; i++) {
				phi[sA[i].index] = sA[i - 1].index;

			}
			int L = 0;
			for (int i = 0; i < n; i++) {
				if (phi[i] == -1)
					plcp[i] = 0;
				else {
					while (i + L < n && phi[i] + L < n
							&& cad[i + L] == cad[phi[i] + L])
						L++;

					plcp[i] = L;
					L = Math.max(L - 1, 0);
				}
			}
			for (int i = 0; i < n; i++) {
				lcp[i] = plcp[sA[i].index];
			}

			return lcp;

		}
	}

	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	public static char[] cad;

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		long inicio = System.currentTimeMillis();
		File archivo = new File("entrada");
		if (archivo.exists()) {
			in = new BufferedReader(new FileReader(archivo));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		String line;

		SuffixArrayClass s = new SuffixArrayClass();

		int times = 0;
		while ((line = in.readLine()) != null) {
			String a = line.trim();
			if (times++ != 0) {
				a = in.readLine().trim();
				out.append("\n");
			}
			String b = in.readLine().trim();
			cad = (a + "#" + b).toCharArray();
			s.init(cad);
			s.construct();
			int[] lcp = s.lpc();
			ArrayList<String> l = new ArrayList<String>();
			HashSet<String> set = new HashSet<String>();
			int max = 0, index = 0;
			for (int i = 0; i < lcp.length; i++) {
				if (max <= lcp[i]
						&& i - 1 > 0
						&& ((s.sA[i].index < a.length() && s.sA[i - 1].index >= a
								.length()) || (s.sA[i].index >= a.length() && s.sA[i - 1].index < a
								.length()))) {
					if (max < lcp[i])
						l.clear();
					max = lcp[i];
					l.add(build(s.sA[i].index, s.sA[i].index + lcp[i]));
				}
			}
			if (l.isEmpty() || max == 0)
				out.append("No common sequence.\n");
			else
				for (int j = 0; j < l.size(); j++) {
					a = l.get(j);
					if (!set.contains(a)) {
						out.append(a + "\n");
						set.add(a);
					}
				}

		}
		System.out.print(out);

		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}

	public static String build(int a, int b) {
		String word = "";
		for (int i = a; i < b; i++)
			word += cad[i];
		return word;
	}
}
