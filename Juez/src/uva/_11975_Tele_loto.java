package uva;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class _11975_Tele_loto {

	public static final int[] MAX = { 35, 40, 45, 1 << 20 };
	public static int used = 0;
	public static boolean[][] m = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int t = Integer.parseInt(line.trim());
			for (int i = 0; i < t; i++) {
				if (i != 0)
					out.append("\n");
				out.append("Case " + (i + 1) + ":\n");
				int[] nl = readInts(in.readLine());
				int n = nl[0], l = nl[1];
				int[] order = readInts(in.readLine());
				int[] value = readInts(in.readLine());
				Point p = new Point(0, 0);
				for (int k = 0; k < l; k++) {
					int[][] ticket = new int[5][5];
					HashMap<Integer, Point> map = new HashMap<Integer, Point>(50);
					for (int j = 0; j < ticket.length; j++) {
						ticket[j] = readInts(in.readLine());
						for (int h = 0; h < ticket.length; h++)
							map.put(ticket[j][h], new Point(j, h));
						Arrays.fill(m[j], false);
					}
					boolean[] ok = new boolean[4];
					long win = 0;
					used = 0;
					for (int j = 0; j < order.length; j++) {
						p = map.get(order[j]);
						if (p != null) {
							if(!m[p.x][p.y])
								used++;
							m[p.x][p.y] = true;
							for (int h = 0; h < ok.length; h++) 
								if (!ok[h] && j < MAX[h] && isAComb(h)) {
									win += value[h];
									ok[h] = true;
								}
						}
					}
					out.append(win + "\n");
				}
			}
		}
		System.out.print(out);
	}

	public static boolean isAComb(int c) {
		switch (c) {
		case 0:
			return m[0][0] && m[0][4] && m[4][0] && m[4][4];
		case 1:
			return m[2][0] && m[2][1] && m[2][2] && m[2][3] && m[2][4];
		case 2:
			return m[0][0] && m[0][4] && m[1][1] && m[1][3] && m[2][2]
					&& m[3][1] && m[3][3] && m[4][0] && m[4][4];
		}
		return used == 25;
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
