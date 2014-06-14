package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _10102_The_path_in_the_colored_field {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			char[][] m = new char[size][size];
			ArrayList<Point> one = new ArrayList<Point>(size*size);
			ArrayList<Point> three = new ArrayList<Point>(size*size);
			for (int i = 0; i < m.length; i++) {
				m[i] = in.readLine().trim().toCharArray();
				for (int j = 0; j < m[i].length; j++) {
					if (m[i][j] == '1')
						one.add(new Point(i, j));
					else if (m[i][j] == '3')
						three.add(new Point(i, j));
				}
			}
			int ans = 0, curMin;
			Point s, t;
			for (int i = 0; i < one.size(); i++) {
				s = one.get(i);
				curMin = 1<<20;
				for (int j = 0; j < three.size(); j++) {
					t = three.get(j);
					curMin = Math.min(curMin,
							Math.abs(s.x - t.x) + Math.abs(s.y - t.y));
				}
				ans = Math.max(ans, curMin);
			}
			out.append(ans+"\n");
		}
		System.out.print(out);
	}
}
