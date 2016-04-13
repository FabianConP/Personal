package live;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2070_Simple_Encryption {

	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		StringBuilder out = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.trim()), count = 0;
			if (n == 0)
				break;
			char[] l = in.readLine().trim().toCharArray();
			int rows = (int) Math.ceil((l.length * 1.0) / n), cols = n;
			char[][] m = new char[rows][cols];
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols && count < l.length; j++)
					m[i][j] = l[count++];
			int r = 0, c = 0, total = cols + rows - 1;
			for (int i = 0; i < total; i++) {
				if (i < n) {
					r = 0;
					c = i;
				} else {
					r = i - cols + 1;
					c = cols - 1;
				}
				while (r < rows && c >= 0) {
					out.append(m[r][c]);
					r++;
					c--;
				}
			}
			out.append("\n");
		}
		System.out.print(out);
	}
}
