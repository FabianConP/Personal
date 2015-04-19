package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _740_Baudot_Data_Communication_Code {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		char[] ns = in.readLine().trim().toCharArray();
		char[] ss = in.readLine().trim().toCharArray();
		while ((line = in.readLine()) != null && line.length() != 0) {
			char[] word = line.trim().toCharArray();
			boolean shift = false;
			for (int i = 0; i < word.length / 5; i++) {
				int n = 0;
				for (int j = 0; j < 5; j++)
					n |= (word[i * 5 + j] - '0') << (4 - j);
				if (n == 27)
					shift = false;
				else if (n == 31)
					shift = true;
				else {
					if (shift)
						out.append(ss[n]);
					else
						out.append(ns[n]);
				}
			}
			out.append("\n");
		}
		System.out.print(out);
	}
}
