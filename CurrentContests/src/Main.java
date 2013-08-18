import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	public static long[] sumatoria;
	public static HashMap<Long, Sum> map;

	public static void fill() {
		for (int i = 1; i < sumatoria.length; i++) {
			sumatoria[i] = i + sumatoria[i - 1];
			map.put(sumatoria[i], new Sum(i, 1, i));
		}
		long cur = 0;
		Sum aux;
		for (int i = 2; i < sumatoria.length; i++) {
			cur = 0;
			for (int j = i + 1; j < sumatoria.length; j++) {
				cur += sumatoria[j];
				if ((aux = map.get(cur)) != null && aux.size < (j - i + 1))
					map.put(cur, new Sum(j - i + 1, 1, i));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		sumatoria = new long[50002];
		map = new HashMap<Long, Sum>(1000000);
		fill();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0 || line.equals("-1"))
				break d;
			long n = Long.parseLong(line);
			Sum aux = map.get(n);
			if (aux != null)
				sb.append(n + " = " + aux.s + " + ... + " + aux.e).append("\n");
			else
				sb.append(n + " = " + n + " + ... + " + n).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}

class Sum {
	int size, s, e;

	public Sum(int size, int s, int e) {
		this.size = size;
		this.s = s;
		this.e = e;
	}

}
