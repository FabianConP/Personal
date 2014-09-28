package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _193_Graph_Coloring {

	public static ArrayList<Integer>[] list;
	public static boolean[] color, ans;
	public static int nBlacks;

	public static void back(int index, int cur) {
		if (index >= list.length) {
			if (cur > nBlacks) {
				nBlacks = cur;
				ans = color.clone();
			}
			return;
		}
		boolean neighBlack = false;
		for (int i = 0; i < list[index].size() && !neighBlack; i++)
			if (color[list[index].get(i)])
				neighBlack = true;
		if (!neighBlack && index != 0) {
			color[index] = true;
			back(index + 1, cur + 1);
			color[index] = false;
		}
		back(index + 1, cur);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int test = Integer.parseInt(line.trim()), v[];
			int nNodes, nEdges;
			for (int i = 0; i < test; i++) {
				v = retInts(in.readLine());
				nNodes = v[0];
				nEdges = v[1];
				list = new ArrayList[nNodes + 1];
				for (int j = 0; j < list.length; j++)
					list[j] = new ArrayList<Integer>();
				color = new boolean[nNodes + 1];
				ans = new boolean[nNodes + 1];
				nBlacks = 0;
				for (int j = 0; j < nEdges; j++) {
					v = retInts(in.readLine());
					list[v[0]].add(v[1]);
					list[v[1]].add(v[0]);
				}
				back(0, 0);
				out.append(nBlacks + "\n");
				for (int j = 1; j < ans.length; j++)
					if (ans[j])
						out.append(j + " ");
				if (nBlacks > 0)
					out.setLength(out.length() - 1);
				out.append("\n");
			}
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
