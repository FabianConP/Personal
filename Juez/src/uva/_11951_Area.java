package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class _11951_Area {

	static long comp[][], area, price;
	static int m[][], rows, cols, k, left, right;

	public static void kadane() {
		long sum = 0;
		int startRow = 0;
		for (int i = 0; i < rows; i++) {
			long cc = comp[i][right] - comp[i][left - 1];
			if (sum + cc > k)
				while (sum + cc > k && startRow < i) {
					sum -= comp[startRow][right] - comp[startRow][left - 1];
					startRow++;
				}
			sum += cc;
			if (sum <= k && area <= (right - left + 1) * (i - startRow + 1))
				if (area < (right - left + 1) * (i - startRow + 1)) {
					area = (right - left + 1) * (i - startRow + 1);
					price = sum;
				} else
					price = Math.min(price, sum);
		}
	}

	public static void getMaxRectangleSum() {
		int ROWS = rows, COLS = cols;
		for (int i = 0; i < COLS; i++)
			for (int j = 0; j < ROWS; j++)
				comp[j][i + 1] = m[j][i] + comp[j][i];
		for (left = 1; left <= COLS; left++) 
			for (right = left; right <= COLS; right++) 
				kadane();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		m = new int[100][100];
		comp = new long[101][101];
		int t = Integer.parseInt(in.readLine().trim());
		for (int tt = 0; tt < t; tt++) {
			int[] nmk = readInts(in.readLine());
			rows = nmk[0];
			cols = nmk[1];
			k = nmk[2];
			for (int i = 0; i < rows; i++)
				readInts(in.readLine(), i);
			area = price = 0;
			getMaxRectangleSum();
			out.append("Case #" + (tt + 1) + ": " + area + " " + price + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}

	public static void readInts(String line, int row) {
		String[] w = Pattern.compile("\\s").split(line.trim());
		for (int i = 0; i < w.length; i++)
			m[row][i] = Integer.parseInt(w[i]);
	}
}
