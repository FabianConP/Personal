package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11764_Jumping_Mario {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int size = Integer.parseInt(in.readLine().trim());
				int[] walls = readInts(in.readLine());
				int[] ans = new int[2];
				for (int j = 1; j < walls.length; j++)
					if (walls[j] > walls[j - 1])
						ans[0]++;
					else if (walls[j] < walls[j - 1])
						ans[1]++;
				out.append("Case "+(i+1)+": "+ans[0]+" "+ans[1]+"\n");
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
