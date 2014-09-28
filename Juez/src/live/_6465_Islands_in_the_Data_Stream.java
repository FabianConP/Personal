package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _6465_Islands_in_the_Data_Stream {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nCase = Integer.parseInt(line.trim());
			for (int i = 0; i < nCase; i++) {
				int[] a = readInts(in.readLine());
				int ans = 0;
				for (int j = 2; j < a.length; j++)
					if (a[j - 1] < a[j])
						ans++;
				out.append((i + 1) + " " + ans + "\n");
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
