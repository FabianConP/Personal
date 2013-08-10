package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class _11242_Tour_de_France {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		do {
			line = in.readLine();
			if (line == null || line.length() == 0 || line.equals("0"))
				break;
			int[] sizer = retInts(line);
			int[] front = retInts(in.readLine());
			int[] rear = retInts(in.readLine());
			double[] all = new double[sizer[0] * sizer[1]];
			int index = 0;
			for (int i = 0; i < front.length; i++)
				for (int j = 0; j < rear.length; j++)
					all[index++] = (1.0 * rear[j]) / front[i];
			Arrays.sort(all);
			double maxSpread = 0;
			for (int i = 0; i < all.length-1; i++)
				maxSpread = Math.max(maxSpread, all[i+1] / all[i]);
			System.out.printf(Locale.US, "%.2f\n", maxSpread);
		} while (line != null && line.length() != 0);
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
