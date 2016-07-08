package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12467_Secret_Word {
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

	static int KMP(char[] p, char[] t) {
		int i = 0, j = 0, m = p.length, n = t.length, len = 0;
		while (i < n) {
			while (j >= 0 && t[i] != p[j])
				j = b[j];
			i++;
			j++;
			len = Math.max(len, j);
			if (j == m)
				j = b[j];
		}
		return len;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			String word = in.readLine().trim();
			char[] p = new char[word.length()];
			char[] t = new char[word.length()];
			for (int i = 0; i < word.length(); i++) {
				char l = word.charAt(i);
				p[i] = l;
				t[word.length() - i - 1] = l;
			}
			preprocess(p);
			int len = KMP(p, t);
			if (len == 0)
				out.append(p[0] + "\n");
			else {
				for (int i = len - 1; i >= 0; i--)
					out.append(p[i]);
				out.append('\n');
			}
		}
		System.out.print(out);
	}

}
