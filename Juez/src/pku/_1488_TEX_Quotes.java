package pku;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1488_TEX_Quotes {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int n = 0;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			char[] a = line.toCharArray();
			for (int i = 0; i < a.length; i++) {
				if (a[i] == '"' && n % 2 == 0) {
					out.append("``");
					n++;
				} else if (a[i] == '"' && n % 2 == 1) {
					out.append("''");
					n++;
				} else
					out.append(a[i]);
			}
			out.append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
