package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _524_Prime_Ring_Problem {

	public static int sizeRing, ring[];
	public static final boolean[] primes = { false, false, true, true, false,
			true, false, true, false, false, false, true, false, true, false,
			false, false, true, false, true, false, false, false, true, false,
			false, false, false, false, true, false, true };
	public static StringBuilder out;

	public static void back(int size, int used, String path) {
		if (size == 0 && used != 2) {
			if (primes[1 + ring[sizeRing - 1]])
				out.append(path.substring(1) + "\n");
			return;
		}
		for (int i = 2; i <= sizeRing; i++)
			if ((used & (1 << i)) == 0 && primes[ring[size - 1] + i]) {
				ring[size] = i;
				back((size + 1) % sizeRing, used | (1 << i), path + " " + i);
				ring[size] = 0;
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		ring = new int[16];
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (times != 1)
				out.append("\n");
			out.append("Case " + times++ + ":\n");
			sizeRing = Integer.parseInt(line.trim());
			ring[0] = 1;
			back(1, 2, " 1");
		}
		System.out.print(out);
	}
}
