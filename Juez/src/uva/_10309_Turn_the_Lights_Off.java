package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _10309_Turn_the_Lights_Off {

	public static int on;
	public static int min, pos;
	public static boolean[][] vis, m, m2;

	public static void back(int steps, int curOn, int col) {
		int aux = 0;
		for (int i = col; i < 10; i++) {
			aux = change(0, i);
			m[0][i] = !m[0][i];
			rest(steps+1, curOn+aux);
			if(col+1<11) back(steps + 1, curOn + aux, i+1);
			change(0, i);
			m[0][i] = !m[0][i];
		}
	}

	public static void copyM() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				m2[i][j] = m[i][j];
	}

	public static void rest(int step, int curOn) {
		copyM();
		for (int row = 1; row < 10; row++) 
			for (int col = 0; col < 10; col++) 
				if (m2[row - 1][col]) {
					curOn--;
					curOn+=change2(row, col);
					step++;
					m2[row][col] = !m2[row][col];
					m2[row-1][col] = !m2[row-1][col];
				}
		if (curOn == 0 && step <= 100)
			if(min>step)
				min = step;
	}

	public static int change(int row, int col) {
		int c = (m[row][col] ? -1 : 1);
		if (col - 1 >= 0) {
			c += (m[row][col - 1] ? -1 : 1);
			m[row][col - 1] = !m[row][col - 1];
		}
		if (col + 1 < 10) {
			c += (m[row][col + 1] ? -1 : 1);
			m[row][col + 1] = !m[row][col + 1];
		}
		if (row + 1 < 10) {
			c += (m[row + 1][col] ? -1 : 1);
			m[row + 1][col] = !m[row + 1][col];
		}
		return c;
	}

	public static int change2(int row, int col) {
		int c = (m2[row][col] ? -1 : 1);
		if (col - 1 >= 0) {
			c += (m2[row][col - 1] ? -1 : 1);
			m2[row][col - 1] = !m2[row][col - 1];
		}
		if (col + 1 < 10) {
			c += (m2[row][col + 1] ? -1 : 1);
			m2[row][col + 1] = !m2[row][col + 1];
		}
		if (row + 1 < 10) {
			c += (m2[row + 1][col] ? -1 : 1);
			m2[row + 1][col] = !m2[row + 1][col];
		}
		return c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		m = new boolean[10][10];
		m2 = new boolean[10][10];
		vis = new boolean[10][10];
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.trim().equals("end"))
				break;
			String name = line;
			on = 0;
			for (int i = 0; i < 10; i++) {
				line = in.readLine();
				Arrays.fill(m[i], false);
				Arrays.fill(vis[i], false);
				for (int j = 0; j < 10; j++)
					if (line.charAt(j) == 'O') {
						m[i][j] = true;
						on++;
					}
			}
			min = Integer.MAX_VALUE;
			pos = 0;
			rest(0, on);
			back(0, on, 0);
			out.append(name + " ");
			if (min == Integer.MAX_VALUE)
				out.append("-1\n");
			else
				out.append(min + "\n");
		}
		System.out.print(out);
	}
}
