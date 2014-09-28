package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10334_Ray_Through_Glasses {

	public static BigInteger[] glass = new BigInteger[1001];

	public static void fillGlass() {
		glass[0] = BigInteger.ONE;
		glass[1] = glass[0].add(BigInteger.ONE);
		for (int i = 2; i < glass.length; i++)
			glass[i] = glass[i - 1].add(glass[i - 2]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillGlass();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			out.append(glass[n]+"\n");
		}
		System.out.print(out);
	}
}
