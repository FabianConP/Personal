package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10023_Square_root {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				if (i != 0)
					out.append("\n");
				in.readLine();
				out.append(squareRoot(new BigInteger(in.readLine())));
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static BigInteger squareRoot(BigInteger num) {
		BigInteger x, y = num, div = new BigInteger("2");
		do {
			x = y;
			y = x.add(num.divide(x)).divide(div);
		} while (x.compareTo(y) != 0);
		return y.abs();
	}

}
