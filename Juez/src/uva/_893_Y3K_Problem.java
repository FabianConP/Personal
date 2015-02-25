package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class _893_Y3K_Problem {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] v = readInts(line);
			if (v[0] == 0 && v[1] == 0 && v[2] == 0 && v[3] == 0)
				break;
			GregorianCalendar c = new GregorianCalendar(v[3], v[2] - 1, v[1]);
			c.add(Calendar.DAY_OF_MONTH, v[0]);
			String ans = c.get(Calendar.DAY_OF_MONTH) + " ";
			ans += (c.get(Calendar.MONTH) + 1) + " ";
			ans += c.get(Calendar.YEAR);
			out.append(ans + "\n");
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
