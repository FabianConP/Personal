package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _11576_Scrolling_Sign {

	static char[] t;
	static int[] b;

	static void preprocess(char[] p) {
		int i = 0, j = -1, m = p.length;
		b = new int[p.length + 1];
		b[0] = -1;
		while (i < m) {
			while (j >= 0 && p[i] != p[j])
				j = b[j];
			b[++i] = ++j;
		}
	}

	static int KMP(char[] p, char[] t, int start, int end) {
		int i = start, j = 0, m = p.length, n = end, max = 0;
		while (i < n) {
			while (j >= 0 && p[j] != t[i])
				j = b[j];
			i++;
			j++;
			if (i == n)
				max = Math.max(max, j);
			if (j == m)
				j = b[j];
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			int[] nk = readInts(in.readLine());
			int n = nk[0], k = nk[1];
			ArrayList<String> words = new ArrayList<>();
			String word;
			int length = 0;
			for (int i = 0; i < k; i++) {
				word = in.readLine().trim();
				words.add(word);
				length += word.length();
			}
			t = new char[length];
			int index = 0;
			for (int i = 0; i < k; i++) {
				char[] p = words.get(i).toCharArray();
				preprocess(p);
				int save = KMP(p, t, Math.max(0, index - p.length), index);
				for (int j = save; j < p.length; j++)
					t[index++] = p[j];
			}
			out.append(index + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split("\\s+");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}

}
