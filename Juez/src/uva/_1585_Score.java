package uva;
import java.io.IOException;
import java.util.Scanner;

public class _1585_Score {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			char[] w = in.next().trim().toCharArray();
			long sum = 0;
			int acum = 0;
			for (int j = 0; j < w.length; j++) {
				if (w[j] == 'O')
					acum++;
				else
					acum = 0;
				sum += acum;
			}
			out.append(sum + "\n");
		}
		System.out.print(out);
	}
}
