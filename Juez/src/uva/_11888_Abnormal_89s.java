package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11888_Abnormal_89s {
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
		int i = 0, j = 0, m = p.length, n = t.length;
		boolean palindrome = false, alindrome = false;
		while (i < n) {
			while (j >= 0 && t[i] != p[j])
				j = b[j];
			i++;
			j++;
			if (j == m) {
				if (i == j)
					palindrome = true;
				else if (i != n)
					alindrome = true;
				j = b[j];
			}
		}
		return alindrome ? 0 : palindrome ? 1 : 2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			char[] word = in.readLine().toCharArray();
			char[] p = new char[word.length];
			char[] t = new char[word.length * 2];
			for (int i = 0; i < word.length; i++)
				t[i] = t[i + word.length] = p[word.length - i - 1] = word[i];
			preprocess(p);
			int res = KMP(p, t);
			if (res == 0)
				out.append("alindrome\n");
			else if (res == 1)
				out.append("palindrome\n");
			else
				out.append("simple\n");
		}
		System.out.print(out);
	}

}
