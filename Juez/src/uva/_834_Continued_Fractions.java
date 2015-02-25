package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class _834_Continued_Fractions {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		BigInteger zero = BigInteger.ZERO;
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] data = line.trim().split(" ");
			BigInteger a = new BigInteger(data[0]);
			BigInteger b = new BigInteger(data[1]);
			int count = 0;
			out.append("[");
			if (a.compareTo(b) < 0) {
				out.append("0;");
				a = b;
				b = new BigInteger(data[0]);
				count++;
			}
			BigInteger div = a.divide(b), res = a.mod(b);
			while (!div.equals(zero) && !res.equals(zero)) {
				out.append(div + (count++ == 0 ? ";" : ","));
				a = b;
				b = res;
				div = a.divide(b);
				res = a.mod(b);
			}
			out.append(a + "]\n");
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
