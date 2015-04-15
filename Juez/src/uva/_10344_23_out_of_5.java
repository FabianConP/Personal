package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _10344_23_out_of_5 {

	public static int[] v;
	public static boolean pos;

	static void solve(int mask, long acum) {
		if (pos)
			return;
		if (mask == 31) {
			if (acum == 23L)
				pos = true;
			return;
		}
		for (int i = 0; i < 5; i++)
			if ((mask & (1 << i)) == 0) {
				solve(mask | (1 << i), acum + v[i]);
				solve(mask | (1 << i), acum - v[i]);
				solve(mask | (1 << i), acum * v[i]);
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			v = readInts(line);
			if (v[0] == 0 && v[1] == 0 && v[2] == 0 && v[3] == 0 && v[4] == 0)
				break;
			pos = false;
			for (int i = 0; i < 5; i++)
				if (!pos)
					solve(1 << i, v[i]);
			out.append((pos ? "Possible" : "Impossible") + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
