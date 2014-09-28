package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _750_8_Queens_Chess_Problem {

	public static int[] data;
	public static int[] board;
	public static int[][] allSol;
	public static int nsol;
	public static StringBuilder out;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		allSol = new int[93][9];
		nsol = 0;
		board = new int[9];
		queens(1);
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				if (i != 0)
					out.append("\n");
				in.readLine();
				data = retInts(in.readLine());
				out.append("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n\n");
				int con = 1;
				for (int j = 0; j < allSol.length; j++)
					if (allSol[j][data[1]] == data[0]) {
						out.append((con <= 9 ? " " : "") + con++ + "     ");
						for (int l = 1; l <= 8; l++)
							out.append(" " + allSol[j][l]);
						out.append("\n");
					}
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void queens(int col) {
		if (col == 9)
			System.arraycopy(board, 1, allSol[nsol++], 1, 8);
		else
			for (int row = 1; row <= 8; row++)
				if (check(row, col)) {
					board[col] = row;
					queens(col + 1);
				}
	}

	public static boolean check(int row, int col) {
		for (int prev = 1; prev < col; prev++)
			if (board[prev] == row // Verify row
					|| (Math.abs(board[prev] - row) == Math.abs(prev - col))) // Verify
																				// diagonals
				return false;
		return true;
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
