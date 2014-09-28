package uva;
import java.io.IOException;
import java.util.Scanner;

public class _382_Perfection {

	public static int[] dp;

	public static void precal() {
		dp = new int[60001];
		for (int i = 1; i < dp.length; i++)
			for (int j = i; j < dp.length; j += i)
				if (j != i)
					dp[j] += i;
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		precal();
		out.append("PERFECTION OUTPUT\n");
		while (scan.hasNext()) {
			int v = scan.nextInt();
			if (v == 0)
				break;
			out.append(format(v) + "  ");
			if (v == dp[v])
				out.append("PERFECT");
			else if (v < dp[v])
				out.append("ABUNDANT");
			else
				out.append("DEFICIENT");
			out.append("\n");
		}
		out.append("END OF OUTPUT\n");
		System.out.print(out);
	}

	public static String format(int n) {
		String f = "    ";
		String nn = n+"";
		return f.substring(0, 5-nn.length())+nn;
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
