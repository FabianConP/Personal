package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _892_Finding_words {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		Queue<Character> q = new LinkedList<>();
		boolean hyphen = false;
		while ((line = in.readLine()) != null) {
			if ((line = line.trim()).equals("#"))
				break;
			char[] l = line.trim().toCharArray();
			for (int i = 0; i < l.length; i++) {
				if (Character.isLetter(l[i]))
					q.add(l[i]);
				else if (l[i] == ' ') {
					while (!q.isEmpty())
						out.append(q.poll());
					if(hyphen)
						out.append('\n');
					hyphen = false;
					out.append(' ');
				}
			}
			if (l[l.length - 1] != '-')
				while (!q.isEmpty())
					out.append(q.poll());
			else
				hyphen = true;
			out.append('\n');
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}

	public static long[] readLongs(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		long a[] = new long[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Long.parseLong(st.nextToken());
		return a;
	}

	public static double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		double a[] = new double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
