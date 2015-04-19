package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _441_Lotto {

	public static int[] v;
	public static StringBuilder out;

	public static void solve(int ind, int l, String p) {
		if (l == 6)
			out.append(p + "\n");
		else
			for (int i = ind; i <= v.length - (6-l); i++) 
				solve(i + 1, l + 1, p + " " + v[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("0"))
				break;
			v = readInts(line);
			Arrays.sort(v,1,v.length);
			if (nCase++ != 1)
				out.append("\n");
			for (int i = 1; i <= v.length-6; i++)
				solve(i + 1, 1, v[i]+"");

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
