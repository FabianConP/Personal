package coj;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class _2434_Mathematician_Ana {
	
	public static ArrayList<Integer> numerosPrimos(int n) {
	    if (n < 2)  return new ArrayList<Integer>();
	    char[] is_composite = new char[(n - 2 >> 5) + 1];
	    final int limit_i = n - 2 >> 1, limit_j = 2 * limit_i + 3;
	    ArrayList<Integer> results = new ArrayList<>((int) Math.ceil(1.25506    * n / Math.log(n)));
	    results.add(2);
	    for (int i = 0; i < limit_i; ++i)
	        if ((is_composite[i >> 4] & 1 << (i & 0xF)) == 0) {
	            results.add(2 * i + 3);
	            for (long j = 4L * i * i + 12L * i + 9; j < limit_j; j += 4 * i + 6)
	                is_composite[(int) (j - 3L >> 5)] |= 1 << (j - 3L >> 1 & 0xF);
	        }
	    return results;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> primos = numerosPrimos(51);
		do{
			int n = scan.nextInt();
			if(n==0)
				break;
			BigInteger ans = BigInteger.ONE;
			for (int i = 0; i < primos.size(); i++) {
				int current = primos.get(i);
				if(current>n)
					break;
				ans = ans.multiply(new BigInteger(current+""));
			}
			System.out.println(ans);
		}while(scan.hasNext());
	}
}
