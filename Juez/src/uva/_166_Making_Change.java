package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _166_Making_Change {

	public static final int V[] = { 1, 2, 4, 10, 20, 40 },
			INF = Integer.MAX_VALUE / 2, MAX_CENTS = 2000;

	public static int infCoin[], c[];

	public static void fillMinCost(int size) {
		infCoin = new int[size + 1];
		Arrays.fill(infCoin, 1, size + 1, INF);
		for (int i = 0; i < V.length; i++)
			for (int j = V[i]; j < infCoin.length; j++)
				infCoin[j] = Math.min(infCoin[j], infCoin[j - V[i]] + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		StringTokenizer st;
		fillMinCost(MAX_CENTS);
		while ((line = in.readLine()) != null && line.length() != 0) {
			st = new StringTokenizer(line, "[ ]+");
			int[] amount = new int[6];
			int size = 0, pos = 0;
			boolean difToZero = false;
			for (int i = 0; i < amount.length; i++) {
				amount[i] = Integer.parseInt(st.nextToken());
				size += amount[i];
				difToZero |= amount[i] != 0;
			}
			if (!difToZero)
				break;
			int[] c = new int[size];
			for (int i = 0; i < V.length; i++)
				for (int j = 0; j < amount[i]; j++)
					c[pos++] = V[i];
			int pay = (int) (Double.parseDouble(st.nextToken()) * 20);
			int[] finiCoin = new int[MAX_CENTS + 1];
			Arrays.fill(finiCoin, 1, MAX_CENTS + 1, INF);
			for (int i = 0; i < c.length; i++)
				for (int j = MAX_CENTS - c[i]; j >= 0; j--)
					if (finiCoin[j] != INF)
						finiCoin[j + c[i]] = Math.min(finiCoin[j + c[i]], finiCoin[j] + 1);
			int ans = INF;
			for (int i = pay; i <= MAX_CENTS; i++)
				ans = Math.min(ans, finiCoin[i] + infCoin[i - pay]);
			out.append(String.format("%3d", ans) + "\n");
		}
		System.out.print(out);
	}
}
