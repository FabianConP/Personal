package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _481_What_Goes_Up {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<Integer> l = new ArrayList<Integer>(1100000);
		int n = 0;
		while ((line = in.readLine()) != null && line.length() != 0)
			l.add(Integer.parseInt(line.trim()));
		int[] a = new int[l.size()];
		for (int i = 0; i < a.length; i++)
			a[i] = l.get(i);
		int[] lis = findLIS(a);
		out.append(lis.length + "\n-\n");
		for (int i = 0; i < lis.length; i++)
			out.append(lis[i] + "\n");
		System.out.print(out);
	}

	public static int search(int[] M, int[] A, int i, int L) {
		int j = 0;
		int k = L - 1;
		while (j <= k) {
			int m = (j + k) / 2;
			if (A[M[m]] < A[i])
				j = m + 1;
			else
				k = m - 1;
		}

		return k;
	}

	public static int[] findLIS(int[] A) {
		int n = A.length;
		int[] M = new int[n];
		int[] P = new int[n];
		M[0] = 0;
		P[0] = -1;
		int L = 1;

		for (int i = 1; i < n; ++i) {
			int j = search(M, A, i, L);
			if (j == -1)
				P[i] = -1;
			else
				P[i] = M[j];

			if (j == L - 1 || A[i] < A[M[j + 1]]) {
				M[j + 1] = i;
				if (j + 2 > L)
					L = j + 2;
			}
		}

		int[] LIS = new int[L];
		n = L - 1;
		int p = M[n];
		while (n >= 0) {
			LIS[n] = A[p];
			p = P[p];
			n--;
		}
		return LIS;
	}
}
