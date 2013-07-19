package uva;
import java.util.ArrayList;

public class _291_The_House_Of_Santa_Claus {

	public static ArrayList<Integer>[] h;
	public static boolean[][] m;
	public static StringBuilder out;

	public static void DFS(int i, String p) {
		for (int j = 0; j < h[i].size(); j++) {
			if (p.length() == 9) {
				out.append(p).append("\n");
				break;
			}
			if (!m[i][h[i].get(j)]) {
				m[i][h[i].get(j)] = m[h[i].get(j)][i] = true;
				DFS(h[i].get(j), p + (h[i].get(j) + 1));
				m[i][h[i].get(j)] = m[h[i].get(j)][i] = false;
			}
		}
	}

	public static void initMatrix() {
		m = new boolean[5][5];
		for (int i = 0; i < m.length; i++)
			m[i][i] = true;
	}

	public static void main(String[] args) {
		h = new ArrayList[5];
		for (int i = 0; i < h.length; i++)
			h[i] = new ArrayList<Integer>();
		h[0].add(1);
		h[0].add(2);
		h[0].add(4);
		h[1].add(0);
		h[1].add(2);
		h[1].add(4);
		h[2].add(0);
		h[2].add(1);
		h[2].add(3);
		h[2].add(4);
		h[3].add(2);
		h[3].add(4);
		h[4].add(0);
		h[4].add(1);
		h[4].add(2);
		h[4].add(3);
		initMatrix();
		out = new StringBuilder();
		DFS(0, "1");
		System.out.print(out);
	}
}
