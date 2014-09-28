package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _11786_Global_Raining_at_Bididibus {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line);
			for (int i = 0; i < size; i++) {
				Stack<Integer> d = new Stack<Integer>();
				char[] a = in.readLine().toCharArray();
				long ans = 0;
				for (int j = 0; j < a.length; j++)
					if (a[j] == '\\')
						d.push(j);
					else if (a[j] == '/' && !d.isEmpty())
						ans += j - d.pop();
				out.append(ans + "\n");
			}
		}
		System.out.print(out);
	}
}
