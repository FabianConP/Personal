package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;

public class _10400_Game_Show_Math {

	static int[][] prev;
	static char[][] op;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int x = 32000;
		int t = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < t; i++) {
			int[] v = readInts(in.readLine());
			int f = v[v.length - 1], lim = x * 2 + 1;
			prev = new int[v.length - 1][lim];
			Arrays.fill(prev[0], -1);
			op = new char[v.length - 1][lim];
			prev[1][v[1] + x] = x;
			op[1][v[1] + x] = '+';
			for (int r = 2; r < v.length - 1; r++)
				for (int c = 0; c < op[0].length; c++)
					if (op[r - 1][c] != 0) {
						if (Math.abs(c - x + v[r]) <= x) {
							prev[r][c + v[r]] = c;
							op[r][c + v[r]] = '+';
						}
						if (Math.abs(c - x - v[r]) <= x && r > 1) {
							prev[r][c - v[r]] = c;
							op[r][c - v[r]] = '-';
						}
						if (Math.abs((c - x) * v[r]) <= x) {
							prev[r][(c - x) * v[r] + x] = c;
							op[r][(c - x) * v[r] + x] = '*';
						}
						if (v[r] != 0 && (c - x) % v[r] == 0) {
							prev[r][((c - x) / v[r]) + x] = c;
							op[r][((c - x) / v[r]) + x] = '/';
						}
					}
			if (op[v.length - 2][f + x] != 0) {
				Stack<String> q = new Stack<String>();
				int row = v.length - 2, p = f + x;
				while (p != -1 && row > 0) {
					q.push(v[row] + "");
					q.push(op[row][p] + "");
					p = prev[row--][p];
				}
				q.pop();
				while (!q.isEmpty())
					out.append(q.pop());
				out.append("=" + f + "\n");
			} else
				out.append("NO EXPRESSION\n");
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
