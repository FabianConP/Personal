package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _785_Grid_Colouring {

	public static char[][] m;

	public static void floodFill(int i, int j, char p, char f) {
		m[i][j] = f;
		if (i + 1 < m.length && m[i + 1][j] != 'X' && m[i + 1][j] != p
				&& m[i + 1][j] == ' ')
			floodFill(i + 1, j, p, f);
		if (i - 1 >= 0 && m[i - 1][j] != 'X' && m[i - 1][j] != p
				&& m[i - 1][j] == ' ')
			floodFill(i - 1, j, p, f);
		if (j + 1 < m[i].length && m[i][j + 1] != 'X' && m[i][j + 1] != p
				&& m[i][j + 1] == ' ')
			floodFill(i, j + 1, p, f);
		if (j - 1 >= 0 && m[i][j - 1] != 'X' && m[i][j - 1] != p
				&& m[i][j - 1] == ' ')
			floodFill(i, j - 1, p, f);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null) {
			ArrayList<String> lines = new ArrayList<String>(31);
			lines.add(line);
			int cols = Math.max(0, line.length());
			while ((line = in.readLine()).length() == 0
					|| !line.startsWith("_")) {
				lines.add(line);
				if (line.length() != 0)
					cols = Math.max(cols, line.length());
			}
			m = new char[lines.size()][cols];
			char[] curLine;
			for (int i = 0; i < lines.size(); i++) {
				curLine = lines.get(i).toCharArray();
				System.arraycopy(curLine, 0, m[i], 0, curLine.length);
			}
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m[i].length; j++)
					if (m[i][j] != 'X' && m[i][j] != ' ' && (int) m[i][j] != 0)
						floodFill(i, j, m[i][j], m[i][j]);
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++)
					if ((int) m[i][j] != 0)
						out.append(m[i][j]);
				out.append("\n");
			}
			out.append(line + "\n");
		}
		System.out.print(out);
	}
}
