package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12150_Pole_Position {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int[] p = new int[n];
			boolean flag = true;
			for (int i = 0; i < n; i++) {
				int[] ab = readInts(in.readLine());
				int d = i + ab[1];
				if (d >= 0 && d < n && p[d] == 0)
					p[d] = ab[0];
				else
					flag = false;
			}
			if (flag)
				for (int i = 0; i < n; i++) {
					if (i != 0)
						out.append(' ');
					out.append(p[i]);
				}
			else
				out.append("-1");
			out.append('\n');
		}
		System.out.print(out);
	}

	static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
