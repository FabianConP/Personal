package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11258_String_Partition {

	static int n;
	static long dp[], LIM;
	static char[] c;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		LIM = Integer.MAX_VALUE;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				c = ("0" + in.readLine().trim()).toCharArray();
				n = c.length;
				dp = new long[n];
				long pow = 1, num = 0;
				for (int j = 1; j < n; j++, pow = 1, num = 0) {
					int stop = Math.max(j - 10, 1);
					for (int k = j; k >= stop; k--) {
						num = (c[k] - '0') * pow + num;
						pow *= 10;
						if (num >= LIM)
							break;
						else
							dp[j] = Math.max(dp[j], dp[k - 1] + num);
					}
				}
				out.append(dp[n - 1] + "\n");
			}
		}
		System.out.print(out);
	}

}