package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _10306_e_Coins {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int times = in.nextInt();
			for (int i = 0; i < times; i++) {
				int m = in.nextInt(), s = in.nextInt();
				int[][] minCoin = new int[s + 1][s + 1];
				for (int j = 0; j < minCoin.length; j++)
					Arrays.fill(minCoin[j], 1 << 15);
				s *= s;
				Coin[] c = new Coin[m];
				for (int j = 0; j < c.length; j++) 
					c[j] = new Coin(in.nextInt(), in.nextInt(), 1);
				Queue<Coin> q = new LinkedList<Coin>();
				q.add(new Coin(0, 0, 0));
				Coin cur;
				int minCoins = Integer.MAX_VALUE;
				while (!q.isEmpty()) {
					cur = q.poll();
					for (int j = 0; j < c.length; j++) {
						if (s(cur.c + c[j].c, cur.t + c[j].t) <= s
								&& minCoin[cur.c + c[j].c][cur.t + c[j].t] > cur.n + 1) {
							minCoin[cur.c + c[j].c][cur.t + c[j].t] = cur.n + 1;
							if (s(cur.c + c[j].c, cur.t + c[j].t) == s)
								minCoins = Math.min(minCoins, cur.n + 1);
							else
								q.add(new Coin(cur.c + c[j].c, cur.t + c[j].t,
										cur.n + 1));
						}
					}
				}
				if (minCoins == Integer.MAX_VALUE)
					out.append("not possible\n");
				else
					out.append(minCoins + "\n");

			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

	public static int s(int a, int b) {
		return a * a + b * b;
	}

	static class Coin {
		int c, t, n;

		public Coin(int c, int t, int n) {
			this.c = c;
			this.t = t;
			this.n = n;
		}

	}

}
