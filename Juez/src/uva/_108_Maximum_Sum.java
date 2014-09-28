package uva;
import java.io.IOException;
import java.util.Scanner;

public class _108_Maximum_Sum {

	public static long[][] dp = new long[101][101];

	public static void cleanDP(int size){
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) 
				dp[i][j] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int size = in.nextInt();
			long[][] m = new long[size + 1][size + 1];
			long max = Long.MIN_VALUE;
			for (int i = 1; i < m.length; i++) 
				for (int j = 1; j < m.length; j++) 
					m[i][j] = in.nextLong();
			for (int sx = 1; sx <= size; sx++) 
				for (int sy = 1; sy <= size; sy++) {
					cleanDP(size+1);
					for (int i = sx; i <= size; i++)
						for (int j = sy; j <= size; j++) {
							dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + m[i][j]- dp[i - 1][j - 1];
							max = Math.max(max, dp[i][j]);
						}
				}
			out.append(max + "\n");
		}
		System.out.print(out);
	}

	public static long[] readInts(String line) {
		String[] w = line.trim().split(" ");
		long[] a = new long[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
