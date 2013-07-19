package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class  _424_Integer_Inquiry  {
	
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(System.in));
		String line = "";
		BigInteger n = new BigInteger("0");
		BigInteger aux = new BigInteger("0");
		d: do {
			line = buf.readLine();
			if (line == null || line.length() == 0)
				break d;
			aux = new BigInteger(line);
			n = n.add(aux);
			if(aux.compareTo(new BigInteger("0"))==0){
				System.out.println(n);
				break d;
			}
		} while (line != null && line.length() != 0);
	}

}
