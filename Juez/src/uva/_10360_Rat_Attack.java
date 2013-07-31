package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _10360_Rat_Attack {

	public static int d;
	public static int[][] map;
	public static Bomb max;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		map = new int[1025][1025];
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int time = 0; time < times; time++) {
				for (int i = 0; i <= 1024; i++)
					Arrays.fill(map[i], 0);
				d = Integer.parseInt(in.readLine());
				int npopul = Integer.parseInt(in.readLine()), minx, miny, maxx,maxy;
				int[] xys = new int[3];
				max = new Bomb(0, 0, 0);
				for (int i = 0; i < npopul; i++) {
					xys = retInts(in.readLine());
					minx = (xys[0] - d)<0?0:xys[0] - d;
					maxx = (xys[0] + d)>1024?1024:xys[0] + d;
					miny = (xys[1] - d)<0?0:xys[1] - d;
					maxy = (xys[1] + d)>1024?1024:xys[1] + d;
					for (int j = minx; j <= maxx; j++) 
						for (int l = miny; l <= maxy; l++) {
							map[l][j]+=xys[2];
							max = max.max(new Bomb(j, l, map[l][j]));
						}
				}
				out.append(max.x + " " + max.y + " " + max.acum + "\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}

class Bomb implements Comparable<Bomb> {
	int x, y;
	long acum;

	public Bomb(int x, int y, long acum) {
		this.x = x;
		this.y = y;
		this.acum = acum;
	}

	@Override
	public int compareTo(Bomb o) {
		if (this.acum == o.acum)
			if (this.x == o.x)
				return -this.y + o.y;
			else
				return -this.x + o.x;
		return (this.acum > o.acum) ? 1 : -1;
	}

	public Bomb max(Bomb o) {
		return this.compareTo(o) > 0 ? this : o;
	}

}
