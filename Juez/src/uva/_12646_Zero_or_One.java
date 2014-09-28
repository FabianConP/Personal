package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12646_Zero_or_One {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] a = readInts(line);
			if(a[0]!=a[1] && a[0]!=a[2])
				out.append("A\n");
			else if(a[0]!=a[1] && a[1]!=a[2])
				out.append("B\n");
			else if(a[0]!=a[2] && a[1]!=a[2])
				out.append("C\n");
			else
				out.append("*\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
