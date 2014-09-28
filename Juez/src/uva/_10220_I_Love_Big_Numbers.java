package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class _10220_I_Love_Big_Numbers {
	
	public static BigInteger factorial(BigInteger n, int c){
		if(c==0)
			return new BigInteger("1");
		else{
			return n.multiply(factorial(n.subtract(new BigInteger("1")), --c));
		}
	}
	
	public static long suma(String n){
		long ans = 0;
		ans += (n.length()-n.replaceAll("9", "").length())*9;
		ans +=(n.length()-n.replaceAll("8", "").length())*8;
		ans +=(n.length()-n.replaceAll("7", "").length())*7;
		ans +=(n.length()-n.replaceAll("6", "").length())*6;
		ans +=(n.length()-n.replaceAll("5", "").length())*5;
		ans +=(n.length()-n.replaceAll("4", "").length())*4;
		ans +=(n.length()-n.replaceAll("3", "").length())*3;
		ans +=(n.length()-n.replaceAll("2", "").length())*2;
		ans +=(n.length()-n.replaceAll("1", "").length());
		return ans;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		int f = 0;
		d: do {
			line = buf.readLine();
			if(line==null || line.length()==0)
				break d;
			int c = Integer.parseInt(line);
			String n  =factorial(new BigInteger(String.valueOf(c)), c).toString();
			sb.append(suma(n)).append("\n");
			//System.out.println(suma(n));
		} while (line != null && line.length()!=0);
		System.out.print(sb);
	}

}
