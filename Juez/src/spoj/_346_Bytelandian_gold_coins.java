package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _346_Bytelandian_gold_coins {

	public static HashMap<Integer, Long> dp;

	public static long solve(int n){
		if(dp.containsKey(n))
			return dp.get(n);
		long coins= Math.max(n, solve(n / 2) + solve(n / 3) + solve(n / 4));
		dp.put(n, coins);
		return coins;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		dp = new HashMap<Integer, Long>();
		dp.put(0, 0L);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			out.append(solve(n) + "\n");
		}
		System.out.print(out);
	}
}
