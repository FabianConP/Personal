package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10487_Closest_Sums {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			if (times == 0)
				break;
			out.append("Case " + nCase++ + ":\n");
			int a[] = new int[times];
			for (int i = 0; i < times; i++)
				a[i] = Integer.parseInt(in.readLine().trim());
			int nQueries = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < nQueries; i++) {
				int q = Integer.parseInt(in.readLine().trim());
				int dif = Integer.MAX_VALUE, sum = 0;
				for (int j = 0; j < a.length; j++)
					for (int k = j + 1; k < a.length; k++)
						if (Math.abs(a[j] + a[k] - q) < dif) {
							dif = Math.abs(a[j] + a[k] - q);
							sum = a[j] + a[k];
						}
				out.append("Closest sum to " + q + " is " + sum + ".\n");
			}
		}
		System.out.print(out);
	}
}
