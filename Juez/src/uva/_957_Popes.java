package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _957_Popes {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 0;
		while ((line = in.readLine()) != null) {
			if (times++ != 0)
				line = in.readLine();
			int inter = Integer.parseInt(line.trim());
			int n = Integer.parseInt(in.readLine().trim());
			int[] v = new int[n];
			for (int i = 0; i < n; i++)
				v[i] = Integer.parseInt(in.readLine().trim());
			int s = 0, e = 0, max = 0, maxY = 0, minY = 0;
			while (s < n && e < n) {
				while (e < n && v[e] - v[s] < inter) {
					if (e - s >= max) {
						max = e - s + 1;
						minY = v[s];
						maxY = v[e];
					}
					e++;
				}
				s++;
			}
			out.append(max + " " + minY + " " + maxY + "\n");
		}
		System.out.print(out);
	}
}
