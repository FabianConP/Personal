package uva;
import java.io.IOException;
import java.util.Scanner;

public class _10684_The_jackpot {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int size = in.nextInt(), n = 0;
			if (size == 0)
				break;
			long cur = 0, max = 0;
			for (int i = 0; i < size; i++) {
				n = in.nextInt();
				if (n + cur > 0)
					cur += n;
				else
					cur = 0;
				max = Math.max(max, cur);
			}
			if (max == 0)
				out.append("Losing streak.\n");
			else
				out.append("The maximum winning streak is " + max + ".\n");
		}
		System.out.print(out);
	}
}
