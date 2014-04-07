package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10298_Power_Strings {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("."))
				break;
			char[] a = line.toCharArray();
			int ans = 1, size = a.length;
			for (int i = 1; i <= size / 2; i++)
				if (size % i == 0) {
					boolean ok = true;
					for (int j = i; j < size && ok; j += i) {
						for (int k = 0; k < i; k++) {
							if (a[k] != a[k + j]) {
								ok = false;
								break;
							}
						}
					}
					if (ok) {
						ans = size / i;
						break;
					}
				}
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
