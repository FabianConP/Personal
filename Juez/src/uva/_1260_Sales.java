package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1260_Sales {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				int size = Integer.parseInt(in.readLine().trim());
				int[] a = readInts(in.readLine());
				int[] ans = new int[a.length];
				for (int j = 1; j < a.length; j++) 
					for (int k = 0; k < j; k++) 
						if (a[j] >= a[k])
							ans[j]++;
				long sum = 0;
				for (int j = 1; j < ans.length; j++)
					sum += ans[j];
				out.append(sum + "\n");
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
