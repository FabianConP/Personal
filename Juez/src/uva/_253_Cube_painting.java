package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class _253_Cube_painting {

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
			String cube = line.trim();
			int[] order = new int[] { 0, 2, 3, 5, 4, 1 }; 
			char[] d1 = new char[6], d2 = new char[6];
			for (int j = 0; j < d1.length; j++)
				d1[order[j]] = cube.charAt(j);
			for (int j = 0; j < d2.length; j++)
				d2[order[j]] = cube.charAt(j + 6);
			boolean ok = false;
			for (int j = 0; j < l.size() && !ok; j++) {
				order = l.get(j);
				ok = true;
				for (int k = 0; k < d1.length; k++) {
					if (d1[order[k]] != d2[k]) {
						ok = false;
						break;
					}
				}
			}
			out.append((ok ? "TRUE" : "FALSE") + "\n");
		}
		System.out.print(out);
	}

}
