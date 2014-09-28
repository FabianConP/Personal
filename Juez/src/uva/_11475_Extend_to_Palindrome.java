package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11475_Extend_to_Palindrome {

	public static class Manacher {
		int[] p; // p[i] = length of longest palindromic substring of t,
					// centered at i
		char[] s; // original string
		char[] t; // transformed string

		public Manacher(String s) {
			this.s = s.toCharArray();
			t = preprocess();
			p = new int[t.length];

			int center = 0, right = 0;
			for (int i = 1; i < t.length - 1; i++) {
				int mirror = 2 * center - i;

				if (right > i)
					p[i] = Math.min(right - i, p[mirror]);

				// attempt to expand palindrome centered at i
				while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
					p[i]++;

				// if palindrome centered at i expands past right,
				// adjust center based on expanded palindrome.
				if (i + p[i] > right) {
					center = i;
					right = i + p[i];
				}
			}
		}

		// Transform s into t.
		// For example, if s = "abba", then t = "$#a#b#b#a#@"
		// the # are interleaved to avoid even/odd-length palindromes uniformly
		// $ and @ are prepended and appended to each end to avoid bounds
		// checking
		public char[] preprocess() {
			char[] t = new char[s.length * 2 + 3];
			t[0] = '$';
			t[s.length * 2 + 2] = '@';
			for (int i = 0; i < s.length; i++) {
				t[2 * i + 1] = '#';
				t[2 * i + 2] = s[i];
			}
			t[s.length * 2 + 1] = '#';
			return t;
		}

		public String substring(int start, int end) {
			String subs = "";
			for (int i = start; i < end; i++)
				subs += s[i];
			return subs;
		}

		// longest palindromic substring
		public String longestPalindromicSubstring() {
			int length = 0; // length of longest palindromic substring
			int center = 0; // center of longest palindromic substring
			for (int i = 1; i < p.length - 1; i++)
				if (p[i] > length) {
					length = p[i];
					center = i;
				}
			return substring((center - 1 - length) / 2,
					(center - 1 + length) / 2);
		}

		// longest palindromic substring centered at index i/2
		public String longestPalindromicSubstring(int i) {
			i = i + 2;
			int length = p[i];
			int center = i;
			return substring((center - 1 - length) / 2,
					(center - 1 + length) / 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			Manacher m = new Manacher(line);
			int max = 0;
			int center = line.length() - 1;
			for (int i = m.p.length - 3; i >= 0; i--)
				if (max < m.p[i] && m.p[i] + i == m.p.length - 2) {
					max = m.p[i];
					center = i;
				}
			out.append(line);
			for (int i = ((center - 1 - max) / 2) - 1; i >= 0; i--)
				out.append(m.s[i]);
			out.append("\n");
		}
		System.out.print(out);
	}
}