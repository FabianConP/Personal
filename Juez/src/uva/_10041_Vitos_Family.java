package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10041_Vitos_Family {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				int[] s = readInts(in.readLine());
				long ans = Long.MAX_VALUE, curDis = 0;
				for (int j = 1; j < s.length; j++) {
					curDis = 0;
					for (int k = 1; k < s.length; k++)
						curDis += Math.abs(s[j] - s[k]);
					ans = Math.min(curDis, ans);
				}
				out.append(ans + "\n");
			}
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

}
