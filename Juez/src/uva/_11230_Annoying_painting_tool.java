package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _11230_Annoying_painting_tool {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			if (v[0] == 0 && v[1] == 0 && v[2] == 0 && v[3] == 0)
				break;
			char[][] map = new char[v[0]][v[1]];
			char[][] init = new char[v[0]][v[1]];
			for (int i = 0; i < map.length; i++) {
				map[i] = in.readLine().trim().toCharArray();
				Arrays.fill(init[i], '0');
			}
			int count = 0;
			for (int i = 0; i <= v[0] - v[2]; i++) 
				for (int j = 0; j <= v[1] - v[3]; j++) 
					if (map[i][j] != init[i][j]) {
						count++;
						for (int r = 0; r < v[2]; r++) 
							for (int c = 0; c < v[3]; c++) 
								init[i + r][j + c] = init[i + r][j + c] == '0' ? '1': '0';
					}
			boolean pos = true;
			f: for (int i = 0; i < v[0]; i++)
				for (int j = 0; j < v[1]; j++)
					if (map[i][j] != init[i][j]) {
						pos = false;
						break f;
					}
			out.append((pos ? count : -1) + "\n");

		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
