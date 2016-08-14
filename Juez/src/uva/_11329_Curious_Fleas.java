package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _11329_Curious_Fleas {

	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] mm = { { 3, 0, 2, 5, 4, 1 }, { 1, 5, 2, 0, 4, 3 }, { 4, 1, 0, 3, 5, 2 }, { 2, 1, 5, 3, 0, 4 } };

	static int build(int map, int r, int c, int f) {
		return (map << 10) + (r << 8) + (c << 6) + f;
	}

	static int row(int all) {
		return (3) & (all >> 8);
	}

	static int col(int all) {
		return (3) & (all >> 6);
	}

	static int fleas(int all) {
		return (0b111111) & all;
	}

	static int map(int all) {
		return ((1 << 17) - 1) & (all >> 10);
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}

	static int move(int map, int fleas, int r, int c, int d, int x, int y) {
		int f = 0;
		int[] m = mm[d];
		for (int i = 0; i < 6; i++)
			f = s(f, m[i], g(fleas, i));
		if (g(f, 5) == 1 && g(map, x * 4 + y) == 0) {
			f = s(f, 5, 0);
			map = s(map, x * 4 + y, 1);
		} else if (g(map, x * 4 + y) == 1 && g(f, 5) == 0) {
			f = s(f, 5, 1);
			map = s(map, x * 4 + y, 0);
		}
		return build(map, x, y, f);
	}

	static int g(int mask, int index) {
		return (mask >> index) & 1;
	}

	static int s(int mask, int index, int v) {
		if (v == 0 && g(mask, index) == 1)
			return mask ^ (1 << index);
		return mask | (v << index);
	}

	static boolean finish(int map, int r, int c) {
		return Integer.bitCount(map) == 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int n = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < n; t++) {
			in.readLine();
			int map = 0;
			int r = 0, c = 0, f, x, y;
			for (int i = 0; i < 4; i++) {
				char[] l = in.readLine().trim().toCharArray();
				for (int j = 0; j < 4; j++)
					if (l[j] == 'X')
						map |= (1 << (4 * i + j));
					else if (l[j] == 'D') {
						r = i;
						c = j;
					}
			}
			int ans = -1;
			int[] dis = new int[1 << 26];
			Arrays.fill(dis, -1);
			Queue<Integer> q = new LinkedList<>();
			int all = build(map, r, c, 0), next;
			dis[all] = 0;
			q.add(all);
			while (!q.isEmpty()) {
				all = q.poll();
				map = map(all);
				r = row(all);
				c = col(all);
				f = fleas(all);
				if (finish(map, r, c)) {
					ans = dis[all];
					break;
				}
				for (int i = 0; i < 4; i++) {
					x = r + dir[i][0];
					y = c + dir[i][1];
					if (check(x, y)) {
						next = move(map, f, r, c, i, x, y);
						if (dis[next] == -1) {
							dis[next] = dis[all] + 1;
							q.add(next);
						}
					}
				}
			}
			if (ans == -1)
				out.append("impossible\n");
			else
				out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
