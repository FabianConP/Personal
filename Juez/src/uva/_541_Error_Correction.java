package uva;
import java.util.Scanner;

public class _541_Error_Correction {

	public static boolean s(int[][] m, int x, int y) {
		boolean ans = true;
		int c1 = 0, c2 = 0, aux = 0;
		if (x != -1 && y != -1) {
			aux = m[x][y];
			m[x][y] = Math.abs(m[x][y] - 1);
		}
		for (int i = 0; i < m.length; i++) {
			c1 = c2 = 0;
			for (int j = 0; j < m.length; j++) {
				c1 += m[i][j];
				c2 += m[j][i];
			}
			if (c1 % 2 == 1 || c2 % 2 == 1) {
				if (x != -1 && y != -1)
					m[x][y] = aux;
				return false;
			}
		}
		return ans;
	}

	public static String v(int[][] m) {
		String ans = "";
		if (s(m, -1, -1))
			return "OK";
		else {
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m.length; j++)
					if (s(m, i, j))
						return "Change bit (" + (i + 1) + "," + (j + 1) + ")";
		}
		return "Corrupt";
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int f = 0;
		d: do {
			int s = scan.nextInt();
			if (s == 0)
				break d;
			int[][] m = new int[s][s];
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m.length; j++)
					m[i][j] = scan.nextInt();
			sb.append(((f++ != 0) ? "\n" : "") + v(m));
		} while (scan.hasNext());
		System.out.println(sb);
	}

}
