package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10309_Turn_the_Lights_Off {

	public static int initialAmountOn;
	public static int minSteps;
	public static int[] m, m2;

	public static void back(int steps, int amountOn, int col) {
		int aux = 0;
		for (int i = col; i < 10; i++) {
			aux = change(0, i, m);
			m[0] ^= 1 << i;
			count(steps + 1, amountOn + aux);
			if (col + 1 < 11)
				back(steps + 1, amountOn + aux, i + 1);
			change(0, i, m);
			m[0] ^= 1 << i;
		}
	}

	public static void copyMatrix() {
		for (int i = 0; i < 10; i++)
			m2[i] = m[i];
	}

	public static void count(int step, int amountOn) {
		copyMatrix();
		for (int row = 1; row < 10; row++)
			for (int col = 0; col < 10; col++)
				if ((m2[row - 1] & (1 << col)) != 0) {
					amountOn--;
					amountOn += change(row, col, m2);
					step++;
					m2[row] ^= 1 << col;
					m2[row - 1] ^= 1 << col;
				}
		if (amountOn == 0 && step <= 100)
			if (minSteps > step)
				minSteps = step;
	}

	public static int change(int row, int col, int[] mm) {
		int c = ((mm[row] & (1 << col)) != 0 ? -1 : 1);
		if (col - 1 >= 0) {
			c += ((mm[row] & (1 << (col - 1))) != 0 ? -1 : 1);
			mm[row] ^= 1 << (col - 1);
		}
		if (col + 1 < 10) {
			c += ((mm[row] & (1 << (col + 1))) != 0 ? -1 : 1);
			mm[row] ^= 1 << (col + 1);
		}
		if (row + 1 < 10) {
			c += ((mm[row + 1] & (1 << col)) != 0 ? -1 : 1);
			mm[row + 1] ^= 1 << col;
		}
		return c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		m = new int[10];
		m2 = new int[10];
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.trim().equals("end"))
				break;
			String name = line;
			initialAmountOn = 0;
			for (int i = 0; i < 10; i++) {
				line = in.readLine();
				m[i] = 0;
				for (int j = 0; j < 10; j++)
					if (line.charAt(j) == 'O') {
						m[i] |= 1 << j;
						initialAmountOn++;
					}
			}
			minSteps = Integer.MAX_VALUE;
			count(0, initialAmountOn);
			back(0, initialAmountOn, 0);
			out.append(name + " ");
			if (minSteps == Integer.MAX_VALUE)
				out.append("-1\n");
			else
				out.append(minSteps + "\n");
		}
		System.out.print(out);
	}
}
