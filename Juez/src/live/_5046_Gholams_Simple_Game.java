package live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5046_Gholams_Simple_Game {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int[] ss = retInts(in.readLine());
				int size = ss[0];
				boolean[] tiles = new boolean[size];
				int[] v = retInts(in.readLine());
				int l = -1, r = -1;
				for (int j = 0; j < size; j++) {
					switch (v[j]) {
					case 0:
						tiles[j] = true;
						break;
					case 2:
						r = j;
						break;
					case 3:
						l = j;
						break;
					}
				}
				long touch = 0;
				int cur = (r != -1) ? r : l;
				if (r != -1)
					while (ss[1] > 0) {
						for (int k = 1 + cur; k < size && ss[1] > 0; k++) {
							if (tiles[k])
								touch++;
							ss[1]--;
							cur++;
						}
						for (int k = cur - 1; k >= 0 && ss[1] > 0; k--) {
							if (tiles[k])
								touch++;
							ss[1]--;
							cur--;
						}
					}
				else
					while (ss[1] > 0) {
						for (int k = cur - 1; k >= 0 && ss[1] > 0; k--) {
							if (tiles[k])
								touch++;
							ss[1]--;
							cur--;
						}
						for (int k = 1 + cur; k < size && ss[1] > 0; k++) {
							if (tiles[k])
								touch++;
							ss[1]--;
							cur++;
						}
					}
				out.append(touch + "\n");
			}
		} while (line.length() != 0 && line != null);
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