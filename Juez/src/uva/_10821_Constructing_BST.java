package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _10821_Constructing_BST {

	public static StringBuilder out;

	public static void solve(int s, int e, int h) {
		int nodes = e - s;
		int child = (1 << (h - 1)) - 1;
		int childRight = Math.min(nodes, child);
		int childLeft = nodes - childRight;
		int id = s + childLeft;
		out.append(" " + id);
		if (childLeft > 0)
			solve(s, s + childLeft - 1, h - 1);
		if (childRight > 0)
			solve(id + 1, e, h - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nh = readInts(line);
			int n = nh[0], h = nh[1];
			if (n == 0 && h == 0)
				break;
			out.append("Case " + nCase++ + ":");
			if (n > (1 << (h)) - 1)
				out.append(" Impossible.\n");
			else {
				solve(1, n, h);
				out.append("\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
