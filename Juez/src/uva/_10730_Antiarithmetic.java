package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;

public class _10730_Antiarithmetic {

	public static int[] a;

	public static boolean isAntiarithmetic() {
		BitSet bs = new BitSet(a.length + 1);
		for (int i = 0; i < a.length; i++) {
			bs.clear();
			for (int j = i + 1; j < a.length; j++) {
				bs.set(a[j], true);
				if ((a[j] + a[i]) % 2 == 0 && bs.get((a[j] + a[i]) / 2))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.trim().equals("0"))
				break;
			a = readInts(line.split(": ")[1]);
			out.append(isAntiarithmetic() ? "yes\n" : "no\n");
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
