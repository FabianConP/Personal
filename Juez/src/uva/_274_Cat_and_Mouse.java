package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class _274_Cat_and_Mouse {

	public static ArrayList<Integer>[] gcat;
	public static ArrayList<Integer>[] gmouse;
	public static boolean[] vcat;
	public static boolean[] vmouse;
	public static int hcat, hmouse, n;

	public static void dfsCat() {
		Stack<Integer> s = new Stack<Integer>();
		s.push(hcat);
		vcat[hcat] = true;
		while (!s.isEmpty()) {
			int cur = s.pop();
			for (int i = 0; i < gcat[cur].size(); i++) {
				int neigh = gcat[cur].get(i);
				if (!vcat[neigh]) {
					vcat[neigh] = true;
					s.push(neigh);
				}
			}
		}
	}

	public static void dfsMouse() {
		Stack<Integer> s = new Stack<Integer>();
		s.push(hmouse);
		vmouse[hmouse] = true;
		while (!s.isEmpty()) {
			int cur = s.pop();
			for (int i = 0; i < gmouse[cur].size(); i++) {
				int neigh = gmouse[cur].get(i);
				if (!vmouse[neigh]) {
					vmouse[neigh] = true;
					s.push(neigh);
				}
			}
		}
	}

	public static boolean cycleMouse() {
		Stack<Point> s = new Stack<Point>();
		boolean[] visit = new boolean[n + 1];
		s.push(new Point(hmouse, 1));
		visit[hmouse] = true;
		while (!s.isEmpty()) {
			Point cur = s.pop();
			visit[cur.x] = false;
			if (cur.x == hmouse && cur.y >= 2)
				return true;
			for (int i = 0; i < gmouse[cur.x].size(); i++) {
				int neigh = gmouse[cur.x].get(i);
				if (!visit[neigh] && !vcat[neigh]) {
					visit[neigh] = true;
					s.push(new Point(neigh, cur.y + 1));
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		int t = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < t; i++) {
			if (i == 0)
				in.readLine();
			int[] ncm = readInts(in.readLine());
			n = ncm[0];
			hcat = ncm[1];
			hmouse = ncm[2];
			gcat = new ArrayList[n + 1];
			gmouse = new ArrayList[n + 1];
			vcat = new boolean[n + 1];
			vmouse = new boolean[n + 1];
			for (int j = 1; j <= n; j++) {
				gcat[j] = new ArrayList<Integer>();
				gmouse[j] = new ArrayList<Integer>();
				vcat[j] = vmouse[j] = false;
			}
			while ((line = in.readLine()) != null && !line.equals("-1 -1")) {
				int[] ab = readInts(line);
				gcat[ab[0]].add(ab[1]);
			}
			while ((line = in.readLine()) != null && line.length() != 0) {
				int[] ab = readInts(line);
				gmouse[ab[0]].add(ab[1]);
			}
			dfsCat();
			dfsMouse();
			boolean first = false;
			for (int j = 1; j <= n; j++)
				if (vcat[j] == vmouse[j] && vcat[j]) {
					first = true;
					break;
				}
			boolean second = cycleMouse();
			if(i!=0)
				out.append("\n");
			if(first)
				out.append("Y ");
			else
				out.append("N ");
			if(second)
				out.append("Y\n");
			else
				out.append("N\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
