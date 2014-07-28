package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10127_Ones {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			int ones = 1, ans = ones;
			while (ones % n != 0) {
				ones = ((ones * 10) % n) + 1;
				ans++;
			}
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
