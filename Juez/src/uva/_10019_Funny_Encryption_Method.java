package uva;
import java.math.BigInteger;
import java.util.Scanner;


public class _10019_Funny_Encryption_Method {
	
	public static String proc(int n){
		String aux = "";
		BigInteger big1 = new BigInteger(String.valueOf(n));
		aux = big1.toString(2);
		int b1 = aux.length()-aux.replaceAll("1", "").length();
		big1 = new BigInteger(big1.toString(),16);
		aux = big1.toString(2);
		int b2 = aux.length()-aux.replaceAll("1", "").length();
		return b1 + " " + b2;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int f = 0;
		d: do {
			int times = scan.nextInt();
			for (int i = 0; i < times; i++) {
				int n = scan.nextInt();
				sb.append(((f++!=0)?"\n":"")+proc(n));
			}
		} while (scan.hasNext());
		System.out.println(sb);
	}

}
