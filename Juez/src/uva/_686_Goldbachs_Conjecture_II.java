package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _686_Goldbachs_Conjecture_II {

	public static boolean[] isPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<Integer> primes = primeNumbers(32769);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line);
			if (n == 0)
				break;
			int curP0 = 0, times = 0;
			for (int i = 0; i < primes.size(); i++) {
				curP0 = primes.get(i);
				if (curP0 > n / 2)
					break;
				else if (isPrime[n - curP0]) 
					times++;
			}
			out.append(times + "\n");
		}
		System.out.print(out);
	}

	public static ArrayList<Integer> primeNumbers(int n) {
		if (n < 2)
			return new ArrayList<Integer>();
		isPrime = new boolean[n];
		char[] is_composite = new char[(n - 2 >> 5) + 1];
		final int limit_i = n - 2 >> 1, limit_j = 2 * limit_i + 3;
		ArrayList<Integer> results = new ArrayList<Integer>(
				(int) Math.ceil(1.25506 * n / Math.log(n)));
		results.add(2);
		isPrime[2] = true;
		for (int i = 0; i < limit_i; ++i)
			if ((is_composite[i >> 4] & 1 << (i & 0xF)) == 0) {
				results.add(2 * i + 3);
				isPrime[2 * i + 3] = true;
				for (long j = 4L * i * i + 12L * i + 9; j < limit_j; j += 4 * i + 6)
					is_composite[(int) (j - 3L >> 5)] |= 1 << (j - 3L >> 1 & 0xF);
			}
		return results;
	}
}
