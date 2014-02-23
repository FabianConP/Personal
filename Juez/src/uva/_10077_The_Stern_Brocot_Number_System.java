package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10077_The_Stern_Brocot_Number_System {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			if (v[0] == 1 && v[1] == 1)
				break;
			int[] l = { 0, 1 }, h = { 1, 0 }, m = { l[0] + h[0], l[1] + h[1] };
			double vv = (1.0 * v[0]) / v[1];
			if (v[0] == 0 && v[1] == 1)
				out.append("R");
			else if (v[0] == 1 && v[1] == 0)
				out.append("L");
			else
				while (Math.abs(toD(v) - toD(m)) > 0.0000000001) {
					if (vv < toD(m)) {
						out.append("L");
						System.arraycopy(m, 0, h, 0, 2);
					} else {
						out.append("R");
						System.arraycopy(m, 0, l, 0, 2);
					}
					m[0] = l[0] + h[0];
					m[1] = l[1] + h[1];
				}
			out.append("\n");
		}
		System.out.print(out);
	}

	public static double toD(int[] a) {
		return (1.0 * a[0]) / (a[1]);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
