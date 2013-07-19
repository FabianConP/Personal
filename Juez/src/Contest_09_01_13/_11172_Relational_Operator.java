package Contest_09_01_13;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class _11172_Relational_Operator {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int times = scan.nextInt();
		f: for (int i = 0; i < times; i++) {
	        BigInteger a1 = scan.nextBigInteger();
	        BigInteger a2 = scan.nextBigInteger();
	        if(a1.compareTo(a2)==0)
	        	System.out.println("=");
	        else if(a1.compareTo(a2)<0)
	        	System.out.println("<");
	        else
	        	System.out.println(">");
		}
    }
}