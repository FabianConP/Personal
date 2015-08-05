package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Stack;

public class _12526_Cellphone_Typing {
	private static class Node {
		Node[] map;
		boolean finish;

		public Node() {
			map = new Node[26];
			finish = false;
		}
	}

	public static void insert(String word, Node tree) {
		char w[] = word.toCharArray();
		int l;
		Node cur = tree;
		for (int i = 0; i < w.length; i++) {
			l = w[i] - 'a';
			if (cur.map[l] == null)
				cur.map[l] = new Node();
			cur = cur.map[l];
		}
		cur.finish = true;
	}

	public static Node tree;
	public static double ans;

	public static void dfs(Node cur, boolean div, int acum, int level) {
		acum += div ? 1 : 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < cur.map.length; i++)
			if (cur.map[i] != null)
				s.push(i);
		if (cur.finish)
			ans += acum;
		if (s.size() > 1)
			while (!s.isEmpty())
				dfs(cur.map[s.pop()], true, acum, level + 1);
		else if (s.size() == 1)
			dfs(cur.map[s.pop()], cur.finish || level == 0, acum, level + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null && line.length() != 0) {
			tree = new Node();
			int n = Integer.parseInt(line);
			for (int i = 0; i < n; i++) {
				String word = in.readLine().trim();
				insert(word, tree);
			}
			ans = 0;
			dfs(tree, false, 0, 0);
			out.append(String.format(Locale.US, "%.2f\n", ans / n));
		}
		System.out.print(out);
	}
}
