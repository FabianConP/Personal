package strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * n = Length of word
 * 
 * Suffix Array O(n log n)
 * 
 * LCP O(n)
 *
 */

public class SuffixArray {
	
	/**
	 * Applications:
	 * 
	 * String Matching O(n log n): Lower and upper bound in sa[]
	 * 
	 * Longest repeated string O(n): With lcp[], looking the same values
	 * 
	 * Longest common substring O(n + k): Add words to the same string with 
	 * separators that the alphabet doesn't contains
	 */

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
	
	public static void main(String[] args) {
		cad = "GATTACA".toCharArray();
		int[] sa = suffixArray();
		//Sorted array with the suffixes of cad
		System.out.println(Arrays.toString(sa));
		//Array with the longest common prefix for each
		//suffix in cad
		int[] lcp = lcp(sa);
		System.out.println(Arrays.toString(lcp));
	}
}
