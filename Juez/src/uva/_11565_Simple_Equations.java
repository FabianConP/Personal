package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11565_Simple_Equations {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int[] abc = readInts(in.readLine());
				int a = abc[0], b = abc[1], c = abc[2];
				int sqrtC = (int) Math.ceil(Math.sqrt(c)), z;
				boolean sol = false;
				for (int x = -sqrtC; x <= sqrtC && !sol; x++)
					for (int y = -sqrtC; y <= sqrtC; y++) {
						z = a - x - y;
						if (x != y && x != z && y != z && (x * y * z) == b
								&& (x * x + y * y + z * z) == c) {
							out.append(x + " " + y + " " + z + "\n");
							sol = true;
							break;
						}
					}
				if (!sol)
					out.append("No solution.\n");
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
