package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12455_Bars {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int n = Integer.parseInt(in.readLine().trim());
				int nbars = Integer.parseInt(in.readLine().trim());
				int[] l = readInts(in.readLine());
				boolean can[] = new boolean[n + 1], pos = n == 0;
				can[0] = true;
				for (int j = 0; j < l.length && !pos; j++)
					for (int k = n - l[j]; k >= 0; k--) {
						can[k + l[j]] |= can[k];
						if (can[n]) {
							pos = true;
							break;
						}
					}
				out.append(pos ? "YES\n" : "NO\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
