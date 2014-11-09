package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class _11060_Beverages {

	public static ArrayList<Integer>[] ady;
	public static HashMap<String, Integer> map;
	public static int[] parents;
	public static String[] map2;
	public static int size;
	public static boolean findAns;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int nCase = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			size = Integer.parseInt(line.trim());
			map = new HashMap<String, Integer>(30);
			map2 = new String[size + 1];
			ady = new ArrayList[size + 1];
			parents = new int[size + 1];
			int id = 1;
			for (int i = 0; i < size; i++) {
				String type = in.readLine().trim();
				map.put(type, id);
				map2[id++] = type;
			}
			for (int i = 0; i <= size; i++)
				ady[i] = new ArrayList<Integer>(30);
			int nRelations = Integer.parseInt(in.readLine().trim());
			for (int r = 0; r < nRelations; r++) {
				String[] ins = in.readLine().trim().split(" ");
				int a = map.get(ins[0]);
				int b = map.get(ins[1]);
				ady[a].add(b);
				parents[b]++;
			}
			PriorityQueue<Integer> p = new PriorityQueue<Integer>(30);
			for (int i = 1; i < parents.length; i++)
				if (parents[i] == 0) 
					p.add(i);
			out.append("Case #" + nCase+++ ": Dilbert should drink beverages in this order:");
			while (!p.isEmpty()) {
				int node = p.poll();
				out.append(" " + map2[node]);
				for (int i = 0; i < ady[node].size(); i++) {
					int neigh = ady[node].get(i);
					parents[neigh]--;
					if (parents[neigh] == 0)
						p.add(neigh);
				}
			}
			out.append(".\n\n");
			in.readLine();
		}
		System.out.print(out);
	}

}
