package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _11222_Only_I_did_it {

	static class Player implements Comparable<Player> {
		int id;
		HashSet<Integer> p;
		Queue<Integer> q;

		public Player(int id) {
			this.id = id;
			p = new HashSet<>();
			q = new LinkedList<>();
		}

		@Override
		public int compareTo(Player o) {
			if (q.size() == o.q.size())
				return id - o.id;
			return o.q.size() - q.size();
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int ncases = in.nextInt();
		for (int i = 0; i < ncases; i++) {
			out.append("Case #" + (i + 1) + ":\n");
			Player[] play = new Player[3];
			int[][] s = new int[3][];
			for (int j = 0; j < play.length; j++) {
				play[j] = new Player(j + 1);
				int size = in.nextInt();
				s[j] = new int[size + 1];
				for (int k = 0; k < size; k++)
					play[j].p.add(s[j][k] = in.nextInt());
				if (size > 0)
					Arrays.sort(s[j], 0, size);
			}
			HashSet<Integer> queued = new HashSet<>();
			for (int j = 0; j < s[0].length - 1; j++)
				if (!play[1].p.contains(s[0][j]) && !play[2].p.contains(s[0][j])) {
					if (!queued.contains(s[0][j])) {
						play[0].q.add(s[0][j]);
						queued.add(s[0][j]);
					}
				}
			queued.clear();
			for (int j = 0; j < s[1].length - 1; j++)
				if (!play[0].p.contains(s[1][j]) && !play[2].p.contains(s[1][j])) {
					if (!queued.contains(s[1][j])) {
						play[1].q.add(s[1][j]);
						queued.add(s[1][j]);
					}
				}
			queued.clear();
			for (int j = 0; j < s[2].length - 1; j++)
				if (!play[0].p.contains(s[2][j]) && !play[1].p.contains(s[2][j])) {
					if (!queued.contains(s[2][j])) {
						play[2].q.add(s[2][j]);
						queued.add(s[2][j]);
					}
				}
			Arrays.sort(play);
			int max = play[0].q.size();
			for (int j = 0; j < play.length; j++) {
				if (play[j].q.size() == max) {
					HashSet<Integer> used = new HashSet<>();
					out.append(play[j].id + " " + play[j].q.size());
					while (!play[j].q.isEmpty()) {
						int v = play[j].q.poll();
						if (!used.contains(v)) {
							out.append(" " + v);
							used.add(v);
						}
					}
					out.append('\n');
				}
			}
		}
		System.out.print(out);
	}
}
