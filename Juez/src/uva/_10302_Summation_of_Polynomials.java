package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;

public class _10302_Summation_of_Polynomials {

	public static void main(String[] args) throws IOException {
		HashMap<Integer, BigInteger> hm = new HashMap<Integer, BigInteger>();
		BigInteger big = new BigInteger("0");
		BigInteger aux = new BigInteger("0");
		for (int i = 0; i < 50000; i++) {
			big = new BigInteger(String.valueOf(i + 1));
			big = big.pow(3);
			aux = aux.add(big);
			hm.put(i + 1, aux);
		}
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(new FileInputStream("in.in")));
		System.setOut(new PrintStream(new File("out.out")));
		int f = 0;
		String line = "";
		StringBuilder sb = new StringBuilder();
		d: do {
			line = buf.readLine();
			if(line==null || line.length()==0)
				break d;
			int n = Integer.parseInt(line);
			System.out.println(hm.get(n));
			//sb.append(((f++!=0)?"\n":"")+hm.get(n));
		} while (line!=null && line.length()!=0);
		//System.out.println(sb);
	}
}
