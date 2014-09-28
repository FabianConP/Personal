package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10212_The_Last_Non_zero_Digit {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] ab = readInts(line);
			long n = 1;
			ab[1] = ab[0] - ab[1] + 1;
			for (int i = ab[0]; i >= ab[1]; i--) {
				n = productLastNonDigitZero(n, i) % 1000000000;
			}
			n = productLastNonDigitZero(n, 1) % 10;
			out.append(n + "\n");
		}
		System.out.print(out);
	}

	public static long productLastNonDigitZero(long n, long prod) {
		n *= prod;
		while (n % 10 == 0)
			n /= 10;
		return n;
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
