package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _712_S_Trees {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int depth = Integer.parseInt(line.trim());
			if (depth == 0)
				break;
			out.append("S-Tree #" + nCase++ + ":\n");
			String[] infoOrder = (" " + in.readLine()).split(" x");
			int[] order = new int[infoOrder.length - 1];
			for (int j = 1; j < infoOrder.length; j++)
				order[j - 1] = Integer.parseInt(infoOrder[j]) - 1;
			char[] path = in.readLine().trim().toCharArray(), descr;
			int m = Integer.parseInt(in.readLine().trim());
			String pos = "";
			char[] ans = new char[depth];
			for (int j = 0; j < m; j++, pos = "") {
				descr = in.readLine().trim().toCharArray();
				for (int i = 0; i < order.length; i++)
					ans[order[i]] = descr[order[i]];
				for (int i = 0; i < ans.length; i++)
					pos += ans[i];
				out.append(path[Integer.parseInt(pos, 2)]);
			}
			out.append("\n\n");
		}
		System.out.print(out);
	}
}
