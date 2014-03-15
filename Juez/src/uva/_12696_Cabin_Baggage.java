package uva;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class _12696_Cabin_Baggage {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int size = (int) in.nextDouble();
			double[] d = new double[4];
			int allow = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < 4; j++)
					d[j] = in.nextDouble();
				if (d[0] <= 56 && d[1] <= 45 && d[2] <= 25 && d[3] <= 7
						|| (d[0] + d[1] + d[2] <= 125 && d[3] <= 7)) {
					out.append("1\n");
					allow++;
				} else
					out.append("0\n");
			}
			out.append(allow + "\n");
		}
		System.out.print(out);
	}
}
