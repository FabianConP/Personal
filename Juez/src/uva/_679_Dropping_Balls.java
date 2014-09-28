package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _679_Dropping_Balls {

	public static int[] a;
	public static boolean[] v;

	public static void precal() {
		int root, child, lim = (1 << 19);
		a = new int[lim + 1];
		v = new boolean[lim + 1];
		lim *= 2;
		for (int i = 1; i < a.length; i++) {
			root = child = 1;
			while (child * 2 < lim) {
				child *= 2;
				if (v[root])
					child++;
				v[root] = !v[root];
				root = child;
			}
			a[i] = child;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		precal();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int ntest = Integer.parseInt(line.trim()), i, d, v[];
			if (ntest == -1)
				break;
			for (int t = 0; t < ntest; t++) {
				v = retInts(in.readLine());
				d = v[0];
				i = v[1];
				v[0] = a[i];
				for (int j = 0; j < 20 - d; j++)
					v[0] = v[0] / 2;
				out.append(v[0] + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
