package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class _10125_Sumsets {

	public static Sum[] s;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int v[] = new int[n], cont = 0;
			n--;
			s = new Sum[(n * (n + 1)) / 2];
			for (int i = 0; i < v.length; i++) {
				v[i] = Integer.parseInt(in.readLine().trim());
				for (int j = 0; j < i; j++)
					s[cont++] = new Sum(v[i], v[j]);
			}
			Arrays.sort(v);
			Arrays.sort(s);
			int ans = -1, index = 0;
			f: for (int i = v.length - 1; i >= 0; i--)
				for (int j = v.length - 1; j >= 0; j--)
					if (v[i] != v[j]) {
						index = upper_bound(v[i] - v[j]) - 1;
						if (index != -1)
							while (index >= 0 && s[index].sum == v[i] - v[j]) {
								if (v[i] != s[index].a && v[i] != s[index].b
										&& v[j] != s[index].a
										&& v[j] != s[index].b) {
									ans = v[i];
									break f;
								}
								index--;
							}
					}
			out.append((ans == -1) ? "no solution\n" : ans + "\n");
		}
		System.out.print(out);
	}

	public static int upper_bound(int key) {
		int lower = 0, upper = s.length - 1, ans = -1;
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (key >= s[mid].sum)
				lower = mid + 1;
			else {
				upper = mid - 1;
				ans = mid;
			}
		}
		return ans;
	}

	public static class Sum implements Comparable<Sum> {
		int a, b, sum;

		public Sum(int a, int b) {
			this.a = a;
			this.b = b;
			sum = a + b;
		}

		@Override
		public int compareTo(Sum arg0) {
			return this.sum - arg0.sum;
		}

	}
}
