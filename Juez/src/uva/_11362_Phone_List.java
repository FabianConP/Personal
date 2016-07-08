package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class _11362_Phone_List {

	static char[] t;
	static int[] b = new int[11], ids;

	static void preprocess(char[] p) {
		int i = 0, j = -1, m = p.length;
		b[0] = -1;
		while (i < m) {
			while (j >= 0 && p[i] != p[j])
				j = b[j];
			b[++i] = ++j;
		}
	}

	static boolean KMP(char[] p, char[] t, int size) {
		int i = 0, j = 0, m = p.length, n = size, c = 0;
		while (i < n) {
			while (j >= 0 && p[j] != t[i])
				j = b[j];
			i++;
			if (++j == m) {
				j = b[j];
				if (i == m)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			int n = Integer.parseInt(in.readLine().trim());
			ArrayList<char[]> nums = new ArrayList<>();
			char[] number;
			int length = 2;
			for (int i = 0; i < n; i++) {
				number = in.readLine().trim().toCharArray();
				nums.add(number);
				length += number.length;
			}
			Collections.sort(nums, new Comparator<char[]>() {
				@Override
				public int compare(char[] o1, char[] o2) {
					int i = 0, j = 0, n = o1.length, m = o2.length;
					char a, b;
					while (true) {
						if (i == n && j == m)
							return 0;
						else if (i == n)
							return 1;
						else if (j == m)
							return -1;
						a = o1[i];
						b = o2[j];
						if (a != b)
							return a - b;
						i++;
						j++;
					}
				}
			});
			ids = new int[length];
			t = new char[length];
			boolean consistent = true;
			for (int i = 1; i < n && consistent; i++) {
				char[] t = nums.get(i - 1);
				char[] p = nums.get(i);
				preprocess(p);
				if (!KMP(p, t, t.length))
					consistent = false;
			}
			out.append(consistent ? "YES\n" : "NO\n");
		}
		System.out.print(out);
	}
}
