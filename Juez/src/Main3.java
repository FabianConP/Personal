import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main3 {

	public static BigInteger[] dp = new BigInteger[15];

	public static void fac() {
		dp[1] = BigInteger.ONE;
		for (long i = 2; i < dp.length; i++)
			dp[(int)i] = dp[(int)i-1].multiply(BigInteger.valueOf(i));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		fac();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int n = Integer.parseInt(line);
			if(n>=0 && n<=7)
				sb.append("Underflow!").append("\n");
			else if(n>=14)
				sb.append("Overflow!").append("\n");
			else if(n<0)
				if(n%2==0)
					sb.append("Underflow!").append("\n");
				else
					sb.append("Overflow!").append("\n");
			else
				sb.append(dp[Integer.parseInt(line)]).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
