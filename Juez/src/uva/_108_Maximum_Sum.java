package uva;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class _108_Maximum_Sum {

	public static long[][] m;
	public static int startRow, endRow, topRow, topCol, bottomRow, bottomCol;

	public static long kadane(long[] comp) {
		long sum = 0, max = Long.MIN_VALUE;
		startRow = endRow = 0;
		for (int i = 0; i < m.length; i++) {
			sum += comp[i];
			if (sum < 0) {
				sum = 0;
				startRow = i + 1;
			} else if (max < sum) {
				max = sum;
				endRow = i;
			}
			max = Math.max(max, comp[i]);
		}
		return max;
	}

	public static long getMaxRectangleSum() {
		int ROWS = m.length, COLS = m[0].length;
		long[] comp = new long[ROWS];
		long maxSum = Long.MIN_VALUE;
		topRow = topCol = bottomRow = bottomCol = 0;
		for (int left = 0; left < COLS; left++) {
			Arrays.fill(comp, 0);
			for (int right = left; right < COLS; right++) {
				for (int r = 0; r < ROWS; r++)
					comp[r] += m[r][right];
				long max = kadane(comp);
				if (maxSum < max) {
					maxSum = max;
					topRow = startRow;
					bottomRow = endRow;
					topCol = left;
					bottomCol = right;
				}
			}
		}
		return maxSum;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int size = in.nextInt();
			m = new long[size][size];
			long max = Long.MIN_VALUE;
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m.length; j++)
					m[i][j] = in.nextLong();
			max = getMaxRectangleSum();
			out.append(max + "\n");
		}
		System.out.print(out);
	}

}
