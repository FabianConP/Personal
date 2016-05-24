package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class _11161_Help_My_Brother_II {

	static BigInteger fibo[], sum[];

	static void fillFibo(int n) {
		fibo = new BigInteger[n + 1];
		sum = new BigInteger[n + 1];
		fibo[0] = BigInteger.ONE;
		fibo[1] = BigInteger.ONE;
		sum[0] = BigInteger.ONE;
		for (int i = 2; i < fibo.length; i++)
			fibo[i] = fibo[i - 1].add(fibo[i - 2]);
		for (int i = 1; i < fibo.length; i++)
			sum[i] = sum[i - 1].add(fibo[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		fillFibo(1501);
		BigInteger ONE = BigInteger.ONE;
		BigInteger TWO = new BigInteger("2");
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			if (n-- == 1)
				out.append("Set " + nCase++ + ":\n0\n");
			else {
				BigInteger x = (sum[n].add(sum[n - 1]).subtract(ONE)).divide(TWO);
				out.append("Set " + nCase++ + ":\n" + x + "\n");
			}
		}
		System.out.print(out);
	}

}
