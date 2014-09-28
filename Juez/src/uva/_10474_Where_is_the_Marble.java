package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _10474_Where_is_the_Marble {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		int[] a = new int[10001];
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			if (v[0] == 0 && v[1] == 0)
				break;
			Arrays.fill(a, Integer.MAX_VALUE);
			ArrayList<Integer> listOfNumbers = new ArrayList<Integer>(v[0]);
			for (int i = 0; i < v[0]; i++) {
				int n = Integer.parseInt(in.readLine().trim());
				listOfNumbers.add(n);
			}
			Collections.sort(listOfNumbers);
			for (int i = 0; i < listOfNumbers.size(); i++) {
				int n = listOfNumbers.get(i);
				a[n] = Math.min(a[n], i + 1);
			}
			out.append("CASE# " + times++ + ":\n");
			for (int i = 0; i < v[1]; i++) {
				int n = Integer.parseInt(in.readLine().trim());
				if (a[n] == Integer.MAX_VALUE)
					out.append(n + " not found\n");
				else
					out.append(n + " found at " + a[n] + "\n");
			}
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
