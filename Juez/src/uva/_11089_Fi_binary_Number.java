package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11089_Fi_binary_Number {

	static int fibo[];

	static void fillFibo(int n) {
		fibo = new int[n + 1];
		fibo[0] = fibo[1] = 1;
		for (int i = 2; i <= n; i++)
			fibo[i] = fibo[i - 1] + fibo[i - 2];
	}

	static int bs(int k) {
		int ans = -1, low = 0, high = fibo.length - 1, mid;
		while (low <= high) {
			mid = (low + high) / 2;
			if (k < fibo[mid])
				high = mid - 1;
			else {
				low = mid + 1;
				ans = mid;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		fillFibo(45);
		int testCases = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < testCases; t++) {
			int n = Integer.parseInt(in.readLine().trim());
			int prev = -1, p;
			while (n != 0) {
				p = bs(n);
				for (int i = p + 1; prev != -1 && i < prev; i++)
					out.append('0');
				out.append('1');
				n -= fibo[p];
				prev = p;
			}
			for (int i = 1; prev != -1 && i < prev; i++)
				out.append('0');
			out.append('\n');
		}
		System.out.print(out);
	}
}
