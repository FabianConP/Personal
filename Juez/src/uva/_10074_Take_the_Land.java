package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class _10074_Take_the_Land {

	static int m[][];

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
					comp[r] += m[r][right];
				long height = kadane(comp);
				maxArea = Math.max(maxArea, height * (right - left + 1));
			}
		}
		return maxArea;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null) {
			int[] nm = readInts(line);
			if(nm[0] == 0 && nm[1] == 0)
				break;
			m = new int[nm[0]][nm[1]];
			for (int i = 0; i < nm[0]; i++)
				m[i] = readInts(in.readLine());
			long area = getMaxRectangleSum();
			out.append(area + "\n");
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

}
