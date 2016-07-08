package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _152_Trees_a_Crowd {
	static int s(int a) {
		return a * a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		ArrayList<int[]> p = new ArrayList<>();
		while ((line = in.readLine()) != null) {
			int[] r = readInts(line);
			if (r[0] == 0 && r[1] == 0 && r[2] == 0)
				break;
			p.add(r);
		}
		int[] cnt = new int[10];
		for (int i = 0; i < p.size(); i++) {
			int[] a = p.get(i);
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < p.size(); j++) {
				int[] b = p.get(j);
				if (i == j)
					continue;
				int dist = s(a[0] - b[0]) + s(a[1] - b[1]) + s(a[2] - b[2]);
				min = Math.min(min, dist);
			}
			for (int k = 0; k < 10; k++)
				if (min < s(k + 1)) {
					cnt[k]++;
					break;
				}
		}
		for (int i = 0; i < 10; i++)
			out.append(String.format("%4d", cnt[i]));
		out.append('\n');
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
