package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12086_Potentiometers {

	static class BIT {
		long tree[];

		public BIT(int n) {
			tree = new long[n];
		}

		public long query(int a, int b) {
			if (a == 0) {
				long sum = 0;
				for (; b >= 0; b = (b & (b + 1)) - 1)
					sum += tree[b];
				return sum;
			}
			return query(0, b) - query(0, a - 1);
		}

		public void increase(int k, long inc) {
			for (; k < tree.length; k |= k + 1)
				tree[k] += inc;
		}

		public void set(int k, long v) {
			increase(k, -query(k, k) + v);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			if (nCase != 1)
				out.append('\n');
			out.append("Case " + nCase++ + ":\n");
			BIT bit = new BIT(n);
			for (int i = 0; i < n; i++) {
				long v = Long.parseLong(in.readLine().trim());
				bit.set(i, v);
			}
			while (!(line = in.readLine()).trim().equals("END")) {
				String[] info = line.split("\\s+");
				if (info[0].equals("M")) {
					int a = Integer.parseInt(info[1]) - 1;
					int b = Integer.parseInt(info[2]) - 1;
					out.append(bit.query(a, b) + "\n");
				} else {
					int a = Integer.parseInt(info[1]) - 1;
					long b = Long.parseLong(info[2]);
					bit.set(a, b);
				}
			}
		}
		System.out.print(out);
	}
}
