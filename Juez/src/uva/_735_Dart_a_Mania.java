package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _735_Dart_a_Mania {

	public static boolean[][][] taken;

	public static int p[], c[], d[];

	public static void all() {
		HashSet<Integer> setD = new HashSet<Integer>(60);
		for (int i = 0; i <= 20; i++) {
			setD.add(i);
			setD.add(i * 2);
			setD.add(i * 3);
		}
		setD.add(50);
		Object[] o = setD.toArray();
		d = new int[o.length];
		for (int i = 0; i < o.length; i++)
			d[i] = (int) o[i];
	}

	public static void solve() {
		taken = new boolean[61][61][61];
		p = new int[181];
		c = new int[181];
		for (int i = 0; i < d.length; i++)
			for (int j = 0; j < d.length; j++)
				for (int k = 0; k < d.length; k++) {
					p[d[i] + d[j] + d[k]]++;
					if (!taken[d[i]][d[j]][d[k]]) {
						taken[d[i]][d[j]][d[k]] = true;
						taken[d[i]][d[k]][d[j]] = true;
						taken[d[j]][d[i]][d[k]] = true;
						taken[d[j]][d[k]][d[i]] = true;
						taken[d[k]][d[i]][d[j]] = true;
						taken[d[k]][d[j]][d[i]] = true;
						c[d[i] + d[j] + d[k]]++;
					}
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		all();
		solve();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n <= 0)
				break;
			if (n >= 0 && n <= 180 && p[n] != 0 && c[n] != 0) {
				out.append("NUMBER OF COMBINATIONS THAT SCORES " + n + " IS "
						+ c[n] + ".\n");
				out.append("NUMBER OF PERMUTATIONS THAT SCORES " + n + " IS "
						+ p[n] + ".\n");
			} else
				out.append("THE SCORE OF " + n
						+ " CANNOT BE MADE WITH THREE DARTS.\n");
			out.append("**********************************************************************\n");
		}
		System.out.print(out);
		System.out.println("END OF OUTPUT");
	}
}
