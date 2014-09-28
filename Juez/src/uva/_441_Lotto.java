package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _441_Lotto {

	public static int[] v;
	public static StringBuilder out;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		boolean start = true;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int[] data = retInts(line);
			if (data[0] == 0)
				break;
			if (!start)
				out.append("\n");
			start = false;
			v = new int[data.length - 1];
			Arrays.sort(v);
			System.arraycopy(data, 1, v, 0, data.length - 1);
			DFS(0, 0, "");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void DFS(int i, int l, String path) {
		if (l == 6)
			out.append(path.trim() + "\n");
		else
			for (int j = i; j < v.length; j++) 
				DFS(j+1, l+1, path+" "+v[j]);
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
