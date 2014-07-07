package live;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class _6466_Von_Neumann_s_Fly {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		int nSets = in.nextInt();
		for (int i = 0; i < nSets; i++) {
			in.nextInt();
			double Xo_A = in.nextDouble();
			double V_A = in.nextDouble();
			double V_B = in.nextDouble();
			double V_F = in.nextDouble();
			double ans = (Xo_A / (V_A + V_B)) * V_F;
			System.out.println((i + 1) + " "
					+ String.format(Locale.US, "%.2f", ans));
		}
	}
}
