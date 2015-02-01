package uva;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;

public class _787_Maximum_Subsequence_Product {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		BigInteger[] v = new BigInteger[100];
		String num;
		while (in.hasNext()) {
			int size = 0;
			BigInteger max = new BigInteger("-9999900");
			while (!(num = in.next()).equals("-999999"))
				v[size++] = new BigInteger(num);
			for (int i = 0; i < size; i++) {
				BigInteger mul = BigInteger.ONE;
				for (int j = i; j < size; j++)
					max = max.max(mul = mul.multiply(v[j]));
			}
			out.append(max + "\n");
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
