package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10285_Longest_Run_on_a_Snowboard {

	public static int m[][], min[][], rows, cols;

	public static int cal(int i, int j, int cur) {
		if (min[i][j] != 0)
			return min[i][j];
		int up, down, left, right;
		up = down = left = right = 0;
		if (i - 1 >= 0 && m[i][j] < m[i - 1][j])
			up = cal(i - 1, j, cur + 1);
		if (j - 1 >= 0 && m[i][j] < m[i][j - 1])
			left = cal(i, j - 1, cur + 1);
		if (i + 1 < rows && m[i][j] < m[i + 1][j])
			down = cal(i + 1, j, cur + 1);
		if (j + 1 < cols && m[i][j] < m[i][j + 1])
			right = cal(i, j + 1, cur + 1);
		return (min[i][j] = 1+max(up, down, left, right));
	}
	
	public static int max(int a, int b, int c, int d){
		return Math.max(Math.max(a, b), Math.max(c, d));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "", data[];
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int test = Integer.parseInt(line.trim());
			String name;
			for (int i = 0; i < test; i++) {
				data = in.readLine().trim().split(" ");
				name = data[0];
				rows = Integer.parseInt(data[1].trim());
				cols = Integer.parseInt(data[2].trim());
				m = new int[rows][cols];
				min = new int[rows][cols];
				for (int j = 0; j < m.length; j++)
					m[j] = retInts(in.readLine());
				int max = 0;
				for (int r = 0; r < m.length; r++) 
					for (int c = 0; c < cols; c++) {
						if(min[r][c]==0)
							cal(r, c, 0);
						max = Math.max(max, min[r][c]);
					}
				out.append(name+": "+max+"\n");
			}
		}
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
