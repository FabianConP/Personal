package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _993_Product_of_digits {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int t = Integer.parseInt(in.readLine().trim());
		for (int tt = 0; tt < t; tt++) {
			int n = Integer.parseInt(in.readLine().trim());
			if (n <= 1)
				out.append(n+"\n");
			else {
				int div = 0;
				boolean pos = true;
				String num = "";
				while (pos && n > 1) {
					div = 0;
					for (int i = 9; i >= 2; i--) {
						while (n % i == 0) {
							n /= i;
							num = i + num;
							div = i;
						}
					}
					pos = div != 0;
				}
				if (pos)
					out.append(num + "\n");
				else
					out.append("-1\n");
			}
		}
		System.out.print(out);
	}
}
