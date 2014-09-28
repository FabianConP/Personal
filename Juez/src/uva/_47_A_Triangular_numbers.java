package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _47_A_Triangular_numbers {

	public static HashSet<Long> set;

	public static void fillSet() {
		set = new HashSet<Long>();
		for (long i = 1; i < 501; i++)
			set.add((i * (i + 1)) / 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillSet();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			long n = Long.parseLong(line);
			if (set.contains(n))
				out.append("YES").append("\n");
			else
				out.append("NO").append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
