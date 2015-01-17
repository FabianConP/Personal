package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;


public class _10667_Largest_Block {
	
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
		StringBuilder out = new StringBuilder();
		int t = Integer.parseInt(in.readLine().trim());
		for(int tt = 0; tt<t; tt++){
			int size = Integer.parseInt(in.readLine().trim());
			m = new int[size][size];
			int inter = Integer.parseInt(in.readLine().trim());
			for (int i = 0; i < inter; i++) {
				int[] d = readInts(in.readLine());
				for (int r = d[0]-1; r < d[2]; r++) 
					for (int c = d[1]-1; c < d[3]; c++) 
						m[r][c] = 1;
			}
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
