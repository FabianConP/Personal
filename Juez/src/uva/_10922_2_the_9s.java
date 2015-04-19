package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10922_2_the_9s {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		BigInteger nine = new BigInteger("9");
		BigInteger zero = BigInteger.ZERO;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int degree = 0;
			BigInteger n = new BigInteger(line.trim());
			if(n.equals(zero))
				break;
			int sum = 0;
			do{
				char[] s = n.toString().toCharArray();
				sum = 0;
				degree++;
				for (int i = 0; i < s.length; i++) 
					sum += s[i]-'0';
				if(n.compareTo(nine)<0)
					break;
				n = BigInteger.valueOf(sum);
			}while(!n.equals(nine));
			if(n.equals(nine))
				out.append(line+" is a multiple of 9 and has 9-degree "+degree+".\n");
			else
				out.append(line+" is not a multiple of 9.\n");
		}
		System.out.print(out);
	}
}
