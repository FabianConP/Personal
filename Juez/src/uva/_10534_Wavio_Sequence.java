package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class _10534_Wavio_Sequence {
	public static int[] lis(int[] array) {
		int[] A = new int[array.length + 1];
		int[] lis = new int[array.length];
		Arrays.fill(A, Integer.MAX_VALUE);
		int longest = 0;
		for (int i = 0; i < array.length; i++) {
			int low = 0;
			int high = array.length - 1;
			while (low < high) {
				int mid = low + (high - low + 1) / 2;
				if (A[mid] >= array[i])
					high = mid - 1;
				else
					low = mid;
			}
			if (A[low + 1] > array[i]) {
				A[low + 1] = array[i];
				longest = (low + 1 > longest) ? low + 1 : longest;
			}
			lis[i] = longest;
		}
		return lis;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] v = new int[n];
			int[] r = new int[n];
			for (int i = 0; i < n; i++)
				v[i] = r[n - i - 1] = in.nextInt();
			int[] lisA = lis(v);
			int[] lisB = lis(r);
			int max = 0;
			for (int i = 0; i < n; i++) 
				max = Math.max(max, Math.min(lisA[i], lisB[n - i - 1]));
			out.append((max * 2) - 1 + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
