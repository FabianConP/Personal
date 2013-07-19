package Contest_09_01_13;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class _424_Integer_Inquiry {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BigInteger total = new BigInteger("0");
        BigInteger aux = new BigInteger("0");
        BigInteger zero = new BigInteger("0");
        w:while(scan.hasNextBigInteger()){
            aux = scan.nextBigInteger();
            if(aux.compareTo(zero)==0)
                break w;
            total = total.add(aux);
        }
        System.out.println(total);
    }
}