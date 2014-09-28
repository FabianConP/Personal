package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _10591_Happy_Number {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int ntimes = Integer.parseInt(line);
			for (int i = 0; i < ntimes; i++) {
				int n = Integer.parseInt(br.readLine()), original = n;
				HashSet<Integer> nums = new HashSet<Integer>();
				nums.add(n);
				boolean unHappy = false;
				while (n != 1) {
					n = trans(n);
					if (nums.contains(n)) {
						unHappy = true;
						break;
					} else
						nums.add(n);
				}
				out.append("Case #" + (i + 1) + ": " + original + " is ");
				if (!unHappy)
					out.append("a Happy number.").append("\n");
				else
					out.append("an Unhappy number.").append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int trans(int n) {
		char[] a = (n + "").toCharArray();
		int ret = 0;
		for (int i = 0; i < a.length; i++)
			ret += Math.pow(Character.digit(a[i], 10), 2);
		return ret;
	}
}
