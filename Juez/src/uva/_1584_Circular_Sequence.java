package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _1584_Circular_Sequence {
	public static char[] s;
	public static Comparator<Integer> comparador = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return s[o1] - s[o2];
		}
	};

	public static int[] suffixArray() {
		int n = s.length;
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++)
			order[i] = n - 1 - i;

		// stable sort of characters
		Arrays.sort(order, comparador);

		int[] sa = new int[n];
		int[] classes = new int[n];
		for (int i = 0; i < n; i++) {
			sa[i] = order[i];
			classes[i] = s[i];
		}
		for (int len = 1; len < n; len *= 2) {
			int[] c = classes.clone();
			for (int i = 0; i < n; i++) {
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
				int s1 = s[i] - len;
				if (s1 >= 0)
					sa[cnt[classes[s1]]++] = s1;
			}
		}
		return sa;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			for (int i = 0; i < n; i++) {
				line = in.readLine().trim();
				int size = line.length();
				s = (line + line).toCharArray();
				int[] sa1 = suffixArray();
				for (int j = 0; j < sa1.length; j++) {
					if (sa1[j] < size) {
						for (int k = 0; k < size; k++)
							out.append(s[sa1[j] + k]);
						break;
					}
				}
				out.append("\n");
			}
		}
		System.out.print(out);
	}
}
