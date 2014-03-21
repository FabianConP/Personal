package live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2557_The_Drunk_Jailer {

	public static final int[] ANS = { 1, 4, 9, 16, 25, 36, 49, 64, 81, 100 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line), n = 0, ans = 0;
			for (int i = 0; i < size; i++) {
				n = Integer.parseInt(in.readLine().trim());
				ans = 0;
				for (int j = 0; j < ANS.length; j++)
					if (n >= ANS[j])
						ans++;
					else
						break;
				out.append(ans + "\n");
			}
		}
		System.out.print(out);
	}

}
