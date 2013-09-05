package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;


public class _369_Combinations {
	public static BigInteger[] fac;
	
public static void fillFac(){
	fac = new BigInteger[101];
	fac[0] = BigInteger.ONE;
	fac[1] = BigInteger.ONE;
	for (long i = 2; i < fac.length; i++) 
		fac[(int)i] = fac[(int)i-1].multiply(BigInteger.valueOf(i));
}
	
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		fillFac();
		StringBuilder out = new StringBuilder();
		while(in.hasNext()){
			int[] d = {in.nextInt(), in.nextInt()};
			if(d[0]+d[1]==0)
				break;
			BigInteger ans = fac[d[0]].divide(fac[d[0]-d[1]].multiply(fac[d[1]]));
			out.append(d[0]+" things taken "+ d[1] +" at a time is "+ ans + " exactly.\n");
		}
		System.out.print(out);

	}
	
	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

}
