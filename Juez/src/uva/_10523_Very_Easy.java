package uva;
import java.math.BigInteger;
import java.util.Scanner;

public class _10523_Very_Easy {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		d: do {
			int a = 0, n = 0;
			BigInteger total = new BigInteger("0");
			BigInteger aux1 = new BigInteger("0");
			BigInteger aux2 = new BigInteger("0");
			n = scan.nextInt();
			a = scan.nextInt();
			aux1 = new BigInteger(a+"");
			for (long i = 1; i <= n; i++) {
				aux2 = BigInteger.valueOf(i);
				total = total.add(aux2.multiply(aux1.pow((int)i)));
			}
			//sb.append(((f++!=0)?"\n":"")+total);
			System.out.println(total);
		} while (scan.hasNext());
	}
}