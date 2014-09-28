package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11389_The_Bus_Driver_Problem {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] ndr = readInts(line);
			int n = ndr[0], d = ndr[1], r = ndr[2];
			if (n == 0 && d == 0 && r == 0)
				break;
			int[] m = readInts(in.readLine());
			int[] e = readInts(in.readLine());
			Arrays.sort(m);
			Arrays.sort(e);
			long pay = 0;
			for (int i = 0; i < m.length; i++)
				if (m[i] + e[n - i - 1] > d)
					pay += (m[i] + e[n - i - 1] - d) * r;
			out.append(pay + "\n");
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
