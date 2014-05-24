package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _725_Division {

	public static StringBuilder out;

	public static boolean dif(int a, int b) {
		char[] numA = f(a).toCharArray();
		char[] numB = f(b).toCharArray();
		int mask = 0;
		for (int i = 0; i < numA.length; i++){
			if ((mask & (1 << (numA[i] - '0'))) == 0)
				mask |= (1 << (numA[i] - '0'));
			else
				return false;
			if ((mask & (1 << (numB[i] - '0'))) == 0)
				mask |= (1 << (numB[i] - '0'));
			else
				return false;
		}
		return true;
	}

	public static String f(int n) {
		return String.format("%05d", n);
	}

	public static boolean cs(int b, int n) {
		boolean existSol = false;
		while (b * n <= 98765) {
			if (dif(b * n, b)){
				out.append(f(b * n) + " / " + f(b) + " = " + n + "\n");
				existSol = true;
			}
			b++;
		}
		return existSol;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		out = new StringBuilder();
		boolean noFirst = false;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line);
			if (n == 0)
				break;
			if(noFirst)
				out.append("\n");
			noFirst = true;
			if(!cs(1234, n))
				out.append("There are no solutions for "+n+".\n");
		}
		System.out.print(out);
	}
}
