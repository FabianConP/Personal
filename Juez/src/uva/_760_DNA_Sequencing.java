package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class _760_DNA_Sequencing {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int t = 0;
		while ((line = in.readLine()) != null) {
			if (t++ != 0) {
				line = in.readLine();
				out.append("\n");
			}
			char[] l1 = line.trim().toCharArray();
			char[] l2 = in.readLine().trim().toCharArray();
			HashSet<String> set = new HashSet<String>(301);
			int max = 0, curSize = 0;
			String cur = "";
			for (int i = 0; i < l1.length; i++) {
				for (int j = 0; j < l2.length; j++, cur = "", curSize = 0) {
					while (i + curSize < l1.length && j + curSize < l2.length
							&& l1[i + curSize] == l2[j + curSize]) {
						curSize++;
						if (curSize > max) {
							max = curSize;
							set.clear();
						}
						cur += l2[j + curSize - 1];
						if (curSize == max)
							set.add(cur);
					}
				}
			}
			if (max == 0)
				out.append("No common sequence.\n");
			else {
				String[] o = (String[]) set.toArray(new String[set.size()]);
				Arrays.sort(o);
				for (int i = 0; i < o.length; i++)
					out.append(o[i] + "\n");
			}
		}
		System.out.print(out);
	}

}
