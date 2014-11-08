package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class _872_Ordering {

	public static ArrayList<Integer>[] ady;
	public static int[] map, parents;
	public static char[] map2;
	public static int size;
	public static ArrayList<String> ans;

	public static void DFS(int node, int used, int parent, String path) {
		if (path.length() / 2 == size)
			ans.add(path.trim());
		else {
			Stack<Integer> change = new Stack<Integer>();
			for (int i = 0; i < ady[node].size(); i++) {
				int neigh = ady[node].get(i);
				if (check(used, neigh)) {
					if (parents[neigh] == 1)
						parent |= (1 << neigh);
					else {
						change.push(neigh);
						parents[neigh]--;
					}
				}
			}
			for (int i = 1; i <= size; i++)
				if (check(used, i) && !check(parent, i)) {
					parents[i]--;
					DFS(i, used | (1 << i), parent | (1 << i), map2[i] + " "
							+ path);
					parents[i]++;
				}
			while (!change.isEmpty())
				parents[change.pop()]++;
		}
	}

	public static boolean check(int map, int pos) {
		return (map & (1 << pos)) == 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCase = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < nCase; t++) {
			in.readLine();
			if (t != 0)
				out.append("\n");
			String[] vars = in.readLine().trim().split(" ");
			size = vars.length;
			map = new int[100];
			map2 = new char[size + 1];
			ady = new ArrayList[size + 1];
			parents = new int[size + 1];
			int id = 1;
			for (int i = 0; i < size; i++) {
				char l = vars[i].charAt(0);
				map[l] = id;
				map2[id++] = l;
			}
			for (int i = 0; i <= size; i++)
				ady[i] = new ArrayList<Integer>(30);
			String[] ins = in.readLine().trim().split(" ");
			for (int i = 0; i < ins.length; i++) {
				int a = map[ins[i].charAt(0)];
				int b = map[ins[i].charAt(2)];
				ady[b].add(a);
				parents[a]++;
			}
			for (int i = 1; i < parents.length; i++)
				if (parents[i] == 0) {
					parents[i]++;
					ady[0].add(i);
				}
			ans = new ArrayList<String>();
			DFS(0, 0, 0, "");
			Collections.sort(ans);
			for (String path : ans)
				out.append(path + "\n");
			if (ans.isEmpty())
				out.append("NO\n");
		}
		System.out.print(out);
	}
}
