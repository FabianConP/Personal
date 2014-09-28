package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class _11959_Dice {

	public static ArrayList<int[]> l;

	public static void functionDFS() {
		l = new ArrayList<int[]>(30);
		Stack<int[]> s = new Stack<int[]>();
		HashSet<String> set = new HashSet<String>(30);
		int[] a = new int[] { 0, 1, 2, 3, 4, 5 };
		String strArray;
		s.add(a);
		while (!s.isEmpty()) {
			a = s.pop();
			strArray = Arrays.toString(a);
			if (!set.contains(strArray)) {
				l.add(a);
				set.add(strArray);
				s.add(rotDown(a));
				s.add(rotLeft(a));
			}
		}
	}

	public static int[] rotLeft(int[] a) {
		int[] r = new int[a.length];
		r[0] = a[0];
		r[2] = a[5];
		r[3] = a[2];
		r[4] = a[3];
		r[5] = a[4];
		r[1] = a[1];
		return r;
	}

	public static int[] rotDown(int[] a) {
		int[] r = new int[a.length];
		r[0] = a[4];
		r[1] = a[2];
		r[2] = a[0];
		r[3] = a[3];
		r[4] = a[1];
		r[5] = a[5];
		return r;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		functionDFS();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				String[] dices = in.readLine().trim().split(" ");
				int[] d1 = new int[6], d2 = new int[6];
				for (int j = 0; j < d1.length; j++)
					d1[j] = dices[0].charAt(j) - '0' - 1;
				for (int j = 0; j < d2.length; j++)
					d2[j] = dices[1].charAt(j) - '0' - 1;
				boolean ok = false;
				for (int j = 0; j < l.size() && !ok; j++) {
					int[] order = l.get(j);
					ok = true;
					for (int k = 0; k < d1.length; k++) {
						if (d1[order[k]] != d2[k]) {
							ok = false;
							break;
						}
					}
				}
				out.append((ok ? "" : "Not ") + "Equal\n");
			}
		}
		System.out.print(out);
	}
}
