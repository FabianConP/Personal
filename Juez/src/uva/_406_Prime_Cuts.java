package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _406_Prime_Cuts {

	public static ArrayList<Integer> p = numerosPrimos(10000);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = retInts(line);
			int pos = 0;
			for (int i = 0; i < p.size(); i++)
				if (p.get(i) <= v[0])
					pos = i;
				else
					break;
			out.append(line + ":");
			if (pos < v[1] * 2) {
				for (int i = 0; i <= pos; i++)
					out.append(" " + p.get(i));
			} else {
				pos--;
				int c = pos / 2;
				if (pos % 2 == 1)
					for (int i = c - v[1] + 2; i <= c + v[1]; i++)
						out.append(" " + p.get(i));
				else
					for (int i = c - v[1] + 1; i <= c + v[1]; i++)
						out.append(" " + p.get(i));
			}
			out.append("\n\n");
		}
		System.out.print(out);
	}

	public static ArrayList<Integer> numerosPrimos(int n) {
		if (n < 2)
			return new ArrayList<Integer>();
		char[] is_composite = new char[(n - 2 >> 5) + 1];
		final int limit_i = n - 2 >> 1, limit_j = 2 * limit_i + 3;
		ArrayList<Integer> results = new ArrayList<>((int) Math.ceil(1.25506
				* n / Math.log(n)));
		results.add(1);
		results.add(2);
		for (int i = 0; i < limit_i; ++i)
			if ((is_composite[i >> 4] & 1 << (i & 0xF)) == 0) {
				results.add(2 * i + 3);
				for (long j = 4L * i * i + 12L * i + 9; j < limit_j; j += 4 * i + 6)
					is_composite[(int) (j - 3L >> 5)] |= 1 << (j - 3L >> 1 & 0xF);
			}
		return results;
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
