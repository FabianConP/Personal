package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10365_Blocks {

	public static long[] box;

	public static void fillBox(int size) {
		box = new long[size + 1];
		long limit, area = 1L << 25L, limit2;
		for (int i = 1; i < box.length; i++) {
			area = 1L << 25L;
			limit = (long) Math.ceil(Math.sqrt(i));
			for (int j = 1; j <= limit; j++)
				if (i % j == 0) {
					limit2 = i / j;
					for (int k = 1; k <= limit2; k++)
						if ((j * k * (i / (j * k))) == i)
							area = Math.min(area, area(j, k, i / (j * k)));
				}
			box[i] = area;
		}
	}

	public static long area(long a, long b, long c) {
		return 2 * (a * b + b * c + a * c);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillBox(1000);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nCase = Integer.parseInt(line.trim());
			for (int i = 0; i < nCase; i++) {
				int n = Integer.parseInt(in.readLine().trim());
				out.append(box[n] + "\n");
			}
		}
		System.out.print(out);
	}
}
