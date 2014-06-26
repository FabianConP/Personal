package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11703_sqrt_log_sin {

	public static long[] cal;
	public static final int MOD = 1000000;

	public static void fill(int size) {
		cal = new long[size + 1];
		cal[0] = 1;
		for (int i = 1; i < cal.length; i++)
			cal[i] = (cal[first(i)] + cal[second(i)] + cal[third(i)]) % MOD;
	}

	public static int first(int i) {
		return (int) Math.floor(i - Math.sqrt(i));
	}

	public static int second(int i) {
		return (int) Math.floor(Math.log(i));
	}

	public static int third(int i) {
		return (int) Math.floor(i * Math.pow(Math.sin(i), 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fill(1000000);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == -1)
				break;
			out.append(cal[n] + "\n");
		}
		System.out.print(out);
	}
}
