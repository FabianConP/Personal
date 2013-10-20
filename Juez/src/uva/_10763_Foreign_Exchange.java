package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10763_Foreign_Exchange {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int[] i = new int[500001], o = new int[500001];
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			if (times == 0)
				break;
			for (int k = 0; k < o.length; k++)
				i[k] = o[k] = 0;
			int[] p;
			for (int j = 0; j < times; j++) {
				p = retInts(in.readLine());
				if (o[p[0]] != 0)
					o[p[0]]--;
				else
					i[p[0]]++;
				if (i[p[1]] != 0)
					i[p[1]]--;
				else
					o[p[1]]++;
			}
			boolean change = true;
			for (int k = 0; k < o.length; k++)
				if (i[k] != 0 || o[k] != 0) {
					change = false;
					break;
				}
			if (change)
				out.append("YES\n");
			else
				out.append("NO\n");
		}
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
