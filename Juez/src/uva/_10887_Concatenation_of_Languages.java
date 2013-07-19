package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _10887_Concatenation_of_Languages {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			for (int t = 0; t < times; t++) {
				int[] mn = returnInts(br.readLine());
				String[] m = new String[mn[0]];
				String[] n = new String[mn[1]];
				for (int i = 0; i < m.length; i++)
					m[i] = br.readLine();
				for (int i = 0; i < n.length; i++)
					n[i] = br.readLine();
				HashSet<String> difWSet = new HashSet<String>(m.length
						* n.length);
				int difWords = 0;
				for (int i = 0; i < m.length; i++)
					for (int j = 0; j < n.length; j++)
						if (!difWSet.contains(m[i] + n[j])) {
							difWords++;
							difWSet.add(m[i] + n[j]);
						}
				out.append("Case "+(t+1)+": "+difWords).append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}
