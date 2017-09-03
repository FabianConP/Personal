package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12856_Counting_substhreengs {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			char[] l = line.trim().toCharArray();
			int[] cnt = new int[3];
			cnt[0] = 1;
			int sum = 0;
			long ans = 0;
			for(int i = 0; i < l.length; i++) 
				if(Character.isDigit(l[i])) {
					sum = (sum + l[i] - '0') % 3;
					ans += cnt[sum]++;
				}else {
					cnt[0] = 1 + (cnt[1] = cnt[2] = 0);
					sum = 0;
				}
			out.append(ans).append('\n');
		}
		System.out.print(out);
	}
}
