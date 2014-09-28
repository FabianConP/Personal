package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5297_Family_Problems {

	public static int[] dp;

	public static void fillDP() {
		dp = new int[1000001];
		int con = 1, lim = 0, ch = (int) 'A';
		for (int i = 0; i < dp.length; i++) {
			if (ch > (int) 'Z')
				ch = (int) 'A';
			if (lim == con) {
				dp[i] = ch;
				lim = 0;
				con++;
				ch++;
			} else {
				lim++;
				dp[i] = ch;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillDP();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int n = Integer.parseInt(line);
			out.append("TERM " + n + " IS " + ((char) dp[n])).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
