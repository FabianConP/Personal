package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11933_Splitting_Numbers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			long n = Long.parseLong(line.trim()), ans[] = new long[2];
			if (n == 0)
				break;
			int con = 0;
			for (int i = 0; i <= 32; i++)
				if ((n & (1L << i)) != 0)
					ans[con++ % 2] |= (n & (1L << i)) != 0 ? (1L << i) : 0;
			out.append(ans[0] + " " + ans[1] + "\n");
		}
		System.out.print(out);
	}
}
