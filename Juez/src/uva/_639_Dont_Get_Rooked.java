package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _639_Dont_Get_Rooked {

	public static char[][] m;
	public static int max, size;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			size = Integer.parseInt(line);
			if (size == 0)
				break;
			m = new char[size][size];
			max = 0;
			for (int i = 0; i < size; i++)
				m[i] = in.readLine().toCharArray();
			rooked(0, 0, 0);
			out.append(max + "\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void rooked(int i, int j, int cur) {
		max = Math.max(max, cur);
		for (int k = 0; k < size; k++) {
			for (int l = 0; l < size; l++) {
				if (m[k][l] == '.' && checkPos(k, l)) {
					m[k][l] = 'o';
					rooked(0, 0, cur + 1);
					m[k][l] = '.';
				}
			}
		}
	}

	public static boolean checkPos(int i, int j) {
		for (int k = i + 1; k < size && m[k][j] != 'X'; k++)
			if (m[k][j] == 'o')
				return false;
		for (int k = 0; k < i && m[k][j] != 'X'; k++)
			if (m[k][j] == 'o')
				return false;
		for (int k = j + 1; k < size && m[i][k] != 'X'; k++)
			if (m[i][k] == 'o')
				return false;
		for (int k = 0; k < j && m[i][k] != 'X'; k++)
			if (m[i][k] == 'o')
				return false;
		return true;
	}
}
