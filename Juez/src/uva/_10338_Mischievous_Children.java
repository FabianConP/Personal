package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class _10338_Mischievous_Children {

	public static BigInteger[] f;
	public static int[] l;

	public static void fillFactorial(int size) {
		f = new BigInteger[size + 1];
		f[0] = BigInteger.ONE;
		for (int i = 1; i <= size; i++)
			f[i] = f[i - 1].multiply(BigInteger.valueOf(i));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillFactorial(20);
		l = new int[27];
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line);
			for (int i = 0; i < size; i++) {
				Arrays.fill(l, 0);
				char[] w = in.readLine().toCharArray();
				for (char c : w)
					l[(int) c - 'A']++;
				BigInteger num = f[w.length];
				BigInteger den = BigInteger.ONE;
				for (int n : l)
					den = den.multiply(f[n]);
				out.append("Data set "+(i+1)+": "+num.divide(den)+"\n");
			}
		}
		System.out.print(out);
	}
}
