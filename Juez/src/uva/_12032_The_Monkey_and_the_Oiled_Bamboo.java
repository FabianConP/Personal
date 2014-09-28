package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12032_The_Monkey_and_the_Oiled_Bamboo {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int size = Integer.parseInt(in.readLine().trim());
				int h[] = readInts(in.readLine()), k = 0;
				for (int j = h.length - 2; j >= 0; j--)
					if (h[j + 1] - h[j] == k)
						k++;
					else if (h[j + 1] - h[j] > k)
						k = h[j + 1] - h[j];
				if (h[0] == k)
					k++;
				else if (h[0] > k)
					k = h[0];
				out.append("Case " + (i + 1) + ": " + k + "\n");
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
