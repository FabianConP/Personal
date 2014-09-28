package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10611_The_Playboy_Chimp {

	public static int[] c;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nChimps = Integer.parseInt(in.readLine().trim());
		c = readInts(in.readLine());
		int nQueries = Integer.parseInt(in.readLine().trim());
		int[] q = readInts(in.readLine().trim());
		int low = 0, high = 0;
		for (int i = 0; i < q.length; i++) {
			low = lower_bound(c, q[i]);
			high = upper_bound(c, q[i]);
			out.append(compute(low) + " " + compute(high) + "\n");
		}
		System.out.print(out);
	}

	public static int upper_bound(int[] arr, int key) {
		int lower = 0, upper = arr.length - 1, ans = -1;
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (key >= arr[mid])
				lower = mid + 1;
			else {
				upper = mid - 1;
				ans = mid;
			}
		}
		return ans;
	}

	public static int lower_bound(int[] arr, int key) {
		int lower = 0, upper = arr.length - 1, ans = -1;
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (key <= arr[mid])
				upper = mid - 1;
			else {
				lower = mid + 1;
				ans = mid;
			}
		}
		return ans;
	}

	public static String compute(int i) {
		if (i < 0 || i >= c.length)
			return "X";
		else
			return "" + c[i];
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
