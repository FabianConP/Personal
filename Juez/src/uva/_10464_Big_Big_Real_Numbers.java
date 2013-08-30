package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class _10464_Big_Big_Real_Numbers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		BigDecimal a, b;
		String[] w;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				line = " " + in.readLine().replaceAll(" ", "  ") + " ";
				line = line.replaceAll(" \\.", "0.").replaceAll(" -\\.", "-0.")
						.replaceAll("\\. ", ".0").replaceAll("  ", " ");
				w = line.trim().split(" ");
				a = new BigDecimal(w[0]);
				b = new BigDecimal(w[1]);
				String ans = a.add(b).toPlainString();
				char[] sum = ans.toCharArray();
				int index = sum.length - 1;
				for (; index >= 1; index--)
					if (sum[index] == '0' && sum[index - 1] == '.')
						break;
					else if (sum[index] != '0')
						break;
				out.append(ans.substring(0, index + 1) + "\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
