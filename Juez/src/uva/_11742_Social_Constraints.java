package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11742_Social_Constraints {

	public static final int[] F = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nm = readInts(line);
			int n = nm[0], m = nm[1];
			if (n == 0 && m == 0)
				break;
			int count = F[n], res[][] = new int[m + 1][3];
			for (int i = 0; i < m; i++)
				res[i] = readInts(in.readLine());
			int[] a = new int[n];
			for (int j = 0; j < a.length; j++)
				a[j] = j;
			int s = 0, e = 0, dif;
			boolean okPerm = true;
			do {
				okPerm = true;
				for (int r = 0; r < m; r++) {
					for (int i = 0; i < a.length; i++) {
						if (res[r][0] == a[i])
							s = i;
						if (res[r][1] == a[i])
							e = i;
					}
					dif = Math.max(s, e) - Math.min(s, e);
					if (res[r][2] < 0 && dif < (-1 * res[r][2]))
						okPerm = false;
					else if (res[r][2] > 0 && dif > res[r][2])
						okPerm = false;
				}
				count = count - (okPerm ? 0 : 1);
			} while (next_permutation(a));
			out.append(Math.max(count, 0) + "\n");
		}
		System.out.print(out);
	}

	public static boolean next_permutation(int[] vec) {
		int tmp;
		for (int i = vec.length - 2; i >= 0; i--)
			if (vec[i + 1] > vec[i])
				for (int j = vec.length - 1; j >= 0; j--)
					if (vec[j] > vec[i]) {
						tmp = vec[i];
						vec[i] = vec[j];
						vec[j] = tmp;
						for (int k = i + 1, l = vec.length - 1; k < l; k++, l--) {
							tmp = vec[k];
							vec[k] = vec[l];
							vec[l] = tmp;
						}
						return true;
					}
		return false;
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
