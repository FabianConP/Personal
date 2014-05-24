package uva;
import java.io.IOException;
import java.util.Scanner;

public class _167_The_Sultans_Successors {

	public static int v[][], board[], allSol[][], nsol;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int boards = in.nextInt();
		v = new int[8][8];
		allSol = new int[93][9];
		nsol = 0;
		board = new int[9];
		queens(1);
		for (int i = 0; i < boards; i++) {
			for (int j = 0; j < 8; j++)
				for (int k = 0; k < 8; k++)
					v[j][k] = in.nextInt();
			int ans = 0, curSum = 0;
			for (int j = 0; j < nsol; j++) {
				curSum = 0;
				for (int k = 1; k <= 8; k++) 
					curSum+=v[k-1][allSol[j][k]-1];
				ans = Math.max(ans, curSum);
			}
			out.append(String.format("%5d", ans) + "\n");
		}
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

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
