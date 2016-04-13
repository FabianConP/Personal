package uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11203_Can_you_decide_it_for_ME {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int n = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < n; i++) {
			char[] l = in.readLine().trim().toCharArray();
			boolean pos = true;
			int x = 0, xx = 0, y = 0, yy = 0, zz = 0, z = 0;
			while (xx < l.length && l[xx] == '?') {
				xx++;
				x++;
			}
			if (xx == l.length || l[xx] != 'M')
				pos = false;
			else {
				yy = xx + 1;
				while (yy < l.length && l[yy] == '?') {
					yy++;
					y++;
				}
				if (yy == l.length || l[yy] != 'E')
					pos = false;
				else {
					zz = yy + 1;
					while (zz < l.length && l[zz] == '?') {
						zz++;
						z++;
					}
					if (zz != l.length)
						pos = false;
				}
			}
			pos &= x + y == z && x != 0 && y != 0 && z != 0;
			out.append(pos ? "theorem\n" : "no-theorem\n");
		}
		System.out.print(out);
	}
}
