package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _121_Pipe_Fitters {
	static double h = Math.sqrt(3) / 2;

	static int skew(double a, double b) {
		int cnt = 0;
		int colseven = (int) (Math.floor(b));
		int colsodd = (int) (Math.floor(b - 0.5));
		int rows = 0;
		if (a >= 1)
			rows = 1 + (int) Math.floor((a - 1) / h);
		cnt = ((rows + 1) / 2) * colseven;
		cnt += ((rows / 2) * colsodd);
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		while ((line = in.readLine()) != null) {
			double[] ab = readDoubles(line);
			double a = ab[0], b = ab[1];
			int grid = (int) (Math.floor(a)) * (int) (Math.floor(b));
			int skew = Math.max(skew(a, b), skew(b, a));
			if (grid >= skew)
				out.append(grid + " grid\n");
			else
				out.append(skew + " skew\n");
		}
		System.out.print(out);
	}

	static double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		double a[] = new double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
