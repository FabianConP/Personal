package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10865_Brownie_Points {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int a = 0, b = 0;
			Point[] p = new Point[n];
			for (int i = 0; i < n; i++) {
				int[] xy = readInts(in.readLine());
				p[i] = new Point(xy[0], xy[1]);
			}
			Point m = p[n / 2];
			for (int i = 0; i < n; i++) {
				if (m.x == p[i].x || m.y == p[i].y)
					continue;
				if ((p[i].x > m.x && p[i].y > m.y) || (p[i].x < m.x && p[i].y < m.y))
					a++;
				else
					b++;
			}
			out.append(a + " " + b + "\n");
		}
		System.out.print(out);
	}

	static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
