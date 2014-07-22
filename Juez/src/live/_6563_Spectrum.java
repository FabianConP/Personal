package live;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _6563_Spectrum {

	public static ArrayList<Integer> ady[];
	public static HashMap<String, Integer> map;
	public static int nNodes, nCase;

	public static final int MAX = 100001;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ady = new ArrayList[MAX];
		map = new HashMap<String, Integer>(MAX);
		nCase = 0;
		boolean firstLine = true;
		reset();
		while ((line = in.readLine()) != null && line.length() != 0) {
			line = line.trim();
			if (firstLine) {
				if (nCase != 1)
					out.append("----------\n");
				out.append("Case " + nCase + ":\n");
			}
			firstLine = false;
			if (line.equals("reset")) {
				reset();
				firstLine = true;
			} else {
				String[] info = line.split(" ");
				if (line.startsWith("add")) {
					if (info.length == 3)
						connect(info[1], info[2]);
					else
						create(info[1]);
				} else if (line.startsWith("connections")) {
					if (exists(info[1])) {
						int index = get(info[1]);
						if (ady[index].isEmpty())
							out.append("no connections\n");
						else {
							int[] count = connections(index);
							for (int i = 0; i < count.length && count[i] != 0; i++)
								out.append(i + ": " + count[i] + "\n");
						}
					} else
						out.append("target does not exist\n");
				} else {
					if (!exists(info[1]) || !exists(info[2]))
						out.append("target does not exist\n");
					else {
						int length = associated(get(info[1]), get(info[2]));
						if (length == -1)
							out.append("no\n");
						else
							out.append("yes: " + length + "\n");
					}
				}
			}
		}
		System.out.print(out);
		System.out.println("----------");
	}

	public static void reset() {
		for (int i = 0; i < ady.length; i++)
			ady[i] = new ArrayList<Integer>();
		map.clear();
		nNodes = 0;
		nCase++;
	}

	public static void connect(String a, String b) {
		int indA = create(a);
		int indB = create(b);
		if (indA != indB) {
			ady[indA].add(indB);
			ady[indB].add(indA);
		}
	}

	public static int create(String a) {
		if (exists(a))
			return get(a);
		map.put(a, nNodes);
		return nNodes++;
	}

	public static boolean exists(String a) {
		return map.containsKey(a);
	}

	public static int get(String a) {
		return map.get(a);
	}

	public static int[] connections(int from) {
		int[] count = new int[nNodes];
		Queue<Point> q = new LinkedList<Point>();
		BitSet vis = new BitSet(nNodes + 1);
		Point e = new Point(from, -1);
		int next = 0;
		q.add(e);
		vis.set(from, true);
		while (!q.isEmpty()) {
			e = q.poll();
			if (e.y != -1)
				count[e.y]++;
			for (int i = 0; i < ady[e.x].size(); i++) {
				next = ady[e.x].get(i);
				if (!vis.get(next)) {
					q.add(new Point(next, e.y + 1));
					vis.set(next, true);
				}
			}
		}
		return count;
	}

	public static int associated(int from, int end) {
		Queue<Point> q = new LinkedList<Point>();
		BitSet vis = new BitSet(nNodes + 1);
		Point node = new Point(from, -1);
		int next = 0;
		q.add(node);
		vis.set(from, true);
		while (!q.isEmpty()) {
			node = q.poll();
			for (int i = 0; i < ady[node.x].size(); i++) {
				next = ady[node.x].get(i);
				if (next == end)
					return node.y + 1;
				if (!vis.get(next)) {
					q.add(new Point(next, node.y + 1));
					vis.set(next, true);
				}
			}
		}
		return -1;
	}

}
