package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _11686_Pick_up_sticks {

	public static ArrayList<Integer>[] ady;
	public static int[] parents;
	public static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] info = line.trim().split(" ");
			int n = Integer.parseInt(info[0]);
			int m = Integer.parseInt(info[1]);
			if (n == 0 && m == 0)
				break;
			size = n;
			ady = new ArrayList[size + 1];
			parents = new int[size + 1];
			int id = 1;
			for (int i = 0; i <= size; i++)
				ady[i] = new ArrayList<Integer>(30);
			int nRelations = m;
			for (int r = 0; r < nRelations; r++) {
				String[] ins = in.readLine().trim().split(" ");
				int a = Integer.parseInt(ins[0]);
				int b = Integer.parseInt(ins[1]);
				ady[a].add(b);
				parents[b]++;
			}
			Queue<Integer> p = new LinkedList<Integer>();
			for (int i = 1; i < parents.length; i++)
				if (parents[i] == 0)
					p.add(i);
			ArrayList<Integer> ans = new ArrayList<Integer>();
			while (!p.isEmpty()) {
				int node = p.poll();
				ans.add(node);
				for (int i = 0; i < ady[node].size(); i++) {
					int neigh = ady[node].get(i);
					parents[neigh]--;
					if (parents[neigh] == 0)
						p.add(neigh);
				}
			}
			if (ans.size() != size)
				out.append("IMPOSSIBLE\n");
			else
				for (Integer node : ans)
					out.append(node + "\n");
		}
		System.out.print(out);
	}

}
