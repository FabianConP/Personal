package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _160_Factors_and_Factorials {

	public static final int[] P = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
			41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int count[] = new int[98], num;
			for (int i = 2; i <= n; i++) {
				num = i;
				for (int j = 0; j < P.length && P[j] <= num; j++)
					while (num >= P[j] && num % P[j] == 0) {
						count[P[j]]++;
						num /= P[j];
					}
			}
			int end = 0;
			out.append(String.format("%3d! =", n));
			for (int i = 0; i < count.length; i++) 
				if (count[i] != 0 && i <= n) {
					if (end % 15 == 0 && end != 0)
						out.append("\n      ");
					out.append(String.format(" %2d", count[i]));
					end++;
				}
			out.append("\n");
		}
		System.out.print(out);
	}
}
