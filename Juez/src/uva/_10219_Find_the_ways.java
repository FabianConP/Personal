package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class _10219_Find_the_ways {
	
	public static BigInteger binomial(final long N, final long K) {
	    BigInteger ret = BigInteger.ONE;
	    for (long k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		int f = 0;
		d: do {
			BigInteger aux = new BigInteger("0");
			line = buf.readLine();
			if(line==null || line.length()==0)
				break d;
			String[] nums = line.split(" ");
			String ans = binomial(Long.parseLong(nums[0]), Long.parseLong(nums[1])).toString();
			sb.append(ans.length()).append("\n");
		} while (line!=null && line.length()!=0);
		System.out.print(sb);
	}

}
