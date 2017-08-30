package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12670_Counting_ones {
	
	static long solve(long n){
		long cur = n, sum = 0, i = 2, p = 0;
		while(cur > 0){
			p = n / i;
			sum += p * (i / 2L);
			p = n % i;
			sum += Math.max(0, p + 1 - (i/ 2L));
			i <<= 1L;
			cur >>= 1L;
		}
		return sum;
	}
	
	
	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			Long[] ab = readLongs(line);
			long sa = solve(ab[0] - 1), sb = solve(ab[1]);
			out.append(sb - sa).append('\n');
		}
		System.out.print(out);
	}

	public static Integer[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		Integer a[] = new Integer[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}

	public static Long[] readLongs(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		Long a[] = new Long[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Long.parseLong(st.nextToken());
		return a;
	}

	public static Double[] readDoubles(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		Double a[] = new Double[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens())
			a[index++] = Double.parseDouble(st.nextToken());
		return a;
	}
}
