package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _514_Rails {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			while (true) {
				int[] c = readInts(in.readLine());
				if (c[0] == 0)
					break;
				Stack<Integer> s = new Stack<>();
				int index = 0;
				for (int i = 1; i <= n; i++) {
					s.push(i);
					while (index < n && !s.isEmpty() && c[index] == s.peek()) {
						s.pop();
						index++;
					}
				}
				if (index == n)
					out.append("Yes\n");
				else
					out.append("No\n");
			}
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
}
