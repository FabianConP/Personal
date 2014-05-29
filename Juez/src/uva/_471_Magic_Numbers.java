package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _471_Magic_Numbers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int times = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < times; i++) {
			if (i != 0)
				out.append("\n");
			in.readLine();
			long n = Long.parseLong(in.readLine().trim()), div = 1;
			while ((div * n) <= 9876543210L) {
				if (dif(div) && dif(div * n))
					out.append((div * n) + " / " + div + " = " + n + "\n");
				div++;
			}
		}
		System.out.print(out);
	}

	public static boolean dif(long n) {
		int mask = 0;
		while (n != 0) {
			if ((mask & (1 << (n % 10L))) != 0)
				return false;
			mask |= (1 << (n % 10L));
			n /= 10L;
		}
		return true;
	}
}
