package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class _10336_Rank_the_Languages {

	public static char[][] m;
	public static HashMap<Character, Integer> map;
	public static ArrayList<Character> al;

	public static void DFS(int i, int j, char l) {
		if (i + 1 < m.length && m[i + 1][j] == l) {
			m[i + 1][j] = '.';
			DFS(i + 1, j, l);
		}
		if (i - 1 >= 0 && m[i - 1][j] == l) {
			m[i - 1][j] = '.';
			DFS(i - 1, j, l);
		}
		if (j + 1 < m[0].length && m[i][j + 1] == l) {
			m[i][j + 1] = '.';
			DFS(i, j + 1, l);
		}
		if (j - 1 >= 0 && m[i][j - 1] == l) {
			m[i][j - 1] = '.';
			DFS(i, j - 1, l);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int[] d = returnInts(br.readLine());
				al = new ArrayList<Character>();
				map = new HashMap<Character, Integer>();
				m = new char[d[0]][d[1]];
				char let;
				for (int j = 0; j < d[0]; j++)
					m[j] = br.readLine().toCharArray();
				for (int j = 0; j < m.length; j++) {
					for (int j2 = 0; j2 < m[i].length; j2++) {
						if (m[j][j2] != '.') {
							if (!map.containsKey(m[j][j2])) {
								map.put(m[j][j2], 1);
								al.add(m[j][j2]);
							} else {
								map.put(m[j][j2], map.get(m[j][j2]) + 1);
							}
							let = m[j][j2];
							m[j][j2] = '.';
							DFS(j, j2, let);
						}
					}
				}
				out.append("World #" + (i + 1)).append("\n");
				ArrayList<Par> al2 = new ArrayList<Par>();
				for (int j = 0; j < al.size(); j++)
					al2.add(new Par(al.get(j), map.get(al.get(j))));
				Collections.sort(al2);
				for (int j = 0; j < al2.size(); j++)
					out.append(al2.get(j)).append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}

class Par implements Comparable<Par> {
	public char l;
	public int can;

	public Par(char l, int can) {
		this.l = l;
		this.can = can;
	}

	@Override
	public int compareTo(Par o) {
		if (this.can == o.can)
			return this.l - o.l;
		return o.can - this.can;
	}

	@Override
	public String toString() {
		return this.l + ": " + this.can;
	}

}
