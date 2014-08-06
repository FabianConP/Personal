package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _11512_GATTACA {

	public static theComparator comp;
	public static char[] cad;

	private static class theComparator implements Comparator<Integer> {
		char[] S;

		public theComparator(char[] s) {
			S = s;
		}

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return S[arg0] - S[arg1];
		}
	}

	public static int[] suffixArray() {
		int n = cad.length;
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++)
			order[i] = n - 1 - i;

		// stable sort of characters
		Arrays.sort(order, comp);

		int[] sa = new int[n];
		int[] classes = new int[n];
		for (int i = 0; i < n; i++) {
			sa[i] = order[i];
			classes[i] = cad[i];
		}
		// sa[i] - suffix on i'th position after sorting by first len characters
		// classes[i] - equivalence class of the i'th suffix after sorting by
		// first len characters

		for (int len = 1; len < n; len *= 2) {
			int[] c = classes.clone();
			for (int i = 0; i < n; i++) {
				// condition sa[i - 1] + len < n simulates 0-symbol at the end
				// of the string
				// a separate class is created for each suffix followed by
				// simulated 0-symbol
				classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]]
						&& sa[i - 1] + len < n
						&& c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]]
						: i;
			}
			// Suffixes are already sorted by first len characters
			// Now sort suffixes by first len * 2 characters
			int[] cnt = new int[n];
			for (int i = 0; i < n; i++)
				cnt[i] = i;
			int[] s = sa.clone();
			for (int i = 0; i < n; i++) {
				// s[i] - order of suffixes sorted by first len characters
				// (s[i] - len) - order of suffixes sorted only by second len
				// characters
				int s1 = s[i] - len;
				// sort only suffixes of length > len, others are already sorted
				if (s1 >= 0)
					sa[cnt[classes[s1]]++] = s1;
			}
		}
		return sa;
	}

	// sort rotations of S in O(n*log(n))
	public static int[] rotationArray() {
		int n = cad.length;
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++)
			order[i] = i;
		Arrays.sort(order, comp);
		int[] sa = new int[n];
		int[] classes = new int[n];
		for (int i = 0; i < n; i++) {
			sa[i] = order[i];
			classes[i] = cad[i];
		}
		for (int len = 1; len < n; len *= 2) {
			int[] c = classes.clone();
			for (int i = 0; i < n; i++)
				classes[sa[i]] = i > 0
						&& c[sa[i - 1]] == c[sa[i]]
						&& c[(sa[i - 1] + len / 2) % n] == c[(sa[i] + len / 2)
								% n] ? classes[sa[i - 1]] : i;
			int[] cnt = new int[n];
			for (int i = 0; i < n; i++)
				cnt[i] = i;
			int[] s = sa.clone();
			for (int i = 0; i < n; i++) {
				int s1 = (s[i] - len + n) % n;
				sa[cnt[classes[s1]]++] = s1;
			}
		}
		return sa;
	}

	// longest common prefixes array in O(n)
	public static int[] lcp(int[] sa) {
		int n = sa.length;
		int[] rank = new int[n];
		for (int i = 0; i < n; i++)
			rank[sa[i]] = i;
		int[] lcp = new int[n - 1];
		for (int i = 0, h = 0; i < n; i++) {
			if (rank[i] < n - 1) {
				for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < cad.length
						&& cad[i + h] == cad[j + h]; ++h)
					;
				lcp[rank[i]] = h;
				if (h > 0)
					--h;
			}
		}
		return lcp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int t = 0; t < nSet; t++) {
				String a = in.readLine().trim();
				cad = a.toCharArray();
				comp = new theComparator(cad);
				int[] sa = suffixArray();
				int[] lcp = lcp(sa);
				int max = 0, times = 0, maxTimes = 0, indStr = 0;
				boolean add = false;
				for (int i = 0; i < lcp.length; i++)
					if (lcp[i] != 0 && max <= lcp[i]) {
						if (max < lcp[i]) {
							indStr = sa[i];
							maxTimes = times = 2;
							add = true;
						} else
							times++;
						max = lcp[i];
						if (add)
							maxTimes = times;
					} else {
						times = 1;
						add = false;
					}
				if (maxTimes < 2 || max == 0)
					out.append("No repetitions found!\n");
				else
					out.append(build(indStr, indStr+max) + " " + maxTimes + "\n");
			}
		}
		System.out.print(out);
	}

	public static String build(int a, int b) {
		String word = "";
		for (int i = a; i < b; i++)
			word += cad[i];
		return word;
	}
}
