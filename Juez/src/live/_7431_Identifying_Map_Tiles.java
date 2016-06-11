package live;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _7431_Identifying_Map_Tiles {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			char[] m = line.trim().toCharArray();
			int n = m.length;
			long row = 0, col = 0;
			long all = 1L << n;
			for (int i = 0; i < n; i++) {
				switch (m[i]) {
				case '1':
					row += all / 2;
					break;
				case '2':
					col += all / 2;
					break;
				case '3':
					row += all / 2;
					col += all / 2;
					break;
				}
				all /= 2L;
			}
			out.append(n + " " + row + " " + col + "\n");
		}
		System.out.print(out);
	}

}
