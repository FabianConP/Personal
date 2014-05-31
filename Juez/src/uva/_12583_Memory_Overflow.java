package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _12583_Memory_Overflow {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				String[] info = in.readLine().trim().split(" ");
				int rem = Integer.parseInt(info[1]);
				char[] names = info[2].toCharArray();
				HashSet<Character> set = new HashSet<Character>(names.length);
				int ans = 0, k;
				for (int j = 1; j < names.length; j++) {
					set.clear();
					k = (j - rem < 0) ? 0 : j - rem;
					for (; k < j; k++)
						if (k >= 0 && !set.contains(names[k]))
							set.add(names[k]);
					if (set.contains(names[j]))
						ans++;
				}
				out.append("Case " + (i + 1) + ": " + ans + "\n");
			}
		}
		System.out.print(out);
	}
}
