package uva;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
 
public class _10176_Ocean_Deep_Make_it_shallow {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String line = "";
        StringBuilder out = new StringBuilder();
        while (in.hasNext()) {
            String num = "";
            BigInteger n, div = new BigInteger("131071");
            while (!(line = in.next()).endsWith("#"))
                num += line.trim();
            num += line.substring(0, line.length() - 1);
            n = new BigInteger(num, 2);
            if(n.mod(div).equals(BigInteger.ZERO))
                out.append("YES\n");
            else
                out.append("NO\n");
        }
        System.out.print(out);
    }
}