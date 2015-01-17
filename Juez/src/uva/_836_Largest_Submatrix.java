package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _836_Largest_Submatrix {

	public static char[][] m;
	public static long area;

	public static long kadane(long[] comp) {
		long sum = 0, max = 0;
		int startRow = 0;
		for (int i = 0; i < m.length; i++) {
			sum += comp[i];
			if (sum != 0) {
				sum = 0;
				startRow = i + 1;
			} else if (max < i - startRow + 1)
				max = i - startRow + 1;
			max = Math.max(max, comp[i] == 0 ? 1 : 0);
		}
		return max;
	}

	public static long getMaxRectangleSum() {
		int ROWS = m.length, COLS = m[0].length;
		long[] comp = new long[ROWS];
		long maxArea = 0;
		for (int left = 0; left < COLS; left++) {
			Arrays.fill(comp, 0);
			for (int right = left; right < COLS; right++) {
				for (int r = 0; r < ROWS; r++)
					comp[r] += (m[r][right] - '0') ^ 1;
				long height = kadane(comp);
				maxArea = Math.max(maxArea, height * (right - left + 1));
			}
		}
		return maxArea;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int times = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < times; t++) {
			in.readLine();
			String firstLine = in.readLine().trim();
			int size = firstLine.length();
			m = new char[size][size];
			m[0] = firstLine.toCharArray();
			for (int j = 1; j < size; j++)
				m[j] = in.readLine().trim().toCharArray();
			area = getMaxRectangleSum();
			if (t != 0)
				out.append("\n");
			out.append(area + "\n");
		}
		System.out.print(out);
	}
}
