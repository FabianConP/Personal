package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class _124_Following_Orders {

	public static ArrayList<Integer>[] ady;
	public static int[] map, parents;
	public static char[] map2;
	public static int size;
	public static ArrayList<String> ans;

	public static void DFS(int node, int used, int parent, String path) {
		if (path.length() == size)
			ans.add(path);
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
					DFS(i, used | (1 << i), parent | (1 << i), path + map2[i]);
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
		String line = "";
		StringBuilder out = new StringBuilder();
		int nCase = 0;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (nCase++ != 0)
				out.append("\n");
			String[] vars = line.trim().split(" ");
			size = vars.length;
			map = new int[200];
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
			for (int i = 0; i < ins.length; i += 2) {
				int a = map[ins[i].charAt(0)];
				int b = map[ins[i + 1].charAt(0)];
				ady[a].add(b);
				parents[b]++;
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
		}
		System.out.print(out);
	}
}
