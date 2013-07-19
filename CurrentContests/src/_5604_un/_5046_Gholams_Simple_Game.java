package _5604_un;

import java.io.IOException;
import java.util.Scanner;

public class _5046_Gholams_Simple_Game {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int times = in.nextInt();
			for (int i = 0; i < times; i++) {
				int size = in.nextInt();
				int steps = in.nextInt();
				boolean[] tiles = new boolean[size];
				int l = -1, r = -1;
				for (int j = 0; j < size; j++) {
					int n = in.nextInt();
					if (n == 0)
						tiles[j] = true;
					else if (n == 2)
						r = j;
					else if (n == 3)
						l = j;
				}
				long touch = 0;
				int cur = 0;
				if (r != -1)
					if (r == size - 1) {
						l = r;
						r = -1;
					} else
						cur = r;
				else {
					if (l == 0) {
						r = l;
						l = -1;
					} else
						cur = l;
				}
				boolean s = true;
				if (r != -1)
					while (steps > 0) {
						for (int k = s ? 1 + cur : 1; k < size && steps > 0; k++) {
							if (tiles[k])
								touch++;
							steps--;
						}
						s = false;
						for (int k = size - 2; k >= 0 && steps > 0; k--) {
							if (tiles[k])
								touch++;
							steps--;
						}
					}
				else
					while (steps > 0) {
						for (int k = s ? cur - 1 : size - 2; k >= 0
								&& steps > 0; k--) {
							if (tiles[k])
								touch++;
							steps--;
						}
						s = false;
						for (int k = 1; k < size && steps > 0; k++) {
							if (tiles[k])
								touch++;
							steps--;
						}
					}
				out.append(touch + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
