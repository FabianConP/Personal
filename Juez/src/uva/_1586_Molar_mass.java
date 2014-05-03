package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _1586_Molar_mass {

	public static char[] f;
	public static final double C = 12.01, H = 1.008, O = 16, N = 14.01;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			String n = "";
			for (int i = 0; i < times; i++) {
				double mass = 0;
				f = (in.readLine().trim() + " ").toCharArray();
				for (int j = 0; j < f.length - 1; j++) {
					if (!Character.isDigit(f[j + 1]))
						mass += atom(f[j]);
					else {
						n = makeNum(j + 1);
						mass += atom(f[j]) * Integer.parseInt(n);
						j += n.length();
					}
				}
				out.append(String.format(Locale.US, "%.3f", mass) + "\n");
			}
		}
		System.out.print(out);
	}

	public static String makeNum(int index) {
		String num = "";
		while (index < f.length && Character.isDigit(f[index]))
			num += f[index++];
		return num;
	}

	public static double atom(char s) {
		switch (s) {
		case 'C':
			return C;
		case 'H':
			return H;
		case 'O':
			return O;
		}
		return N;
	}
}
