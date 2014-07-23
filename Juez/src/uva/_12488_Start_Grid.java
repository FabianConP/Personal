package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12488_Start_Grid {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			int[] map = new int[size + 1];
			int[] ini = readInts(in.readLine());
			for (int i = 0; i < ini.length; i++)
				map[ini[i]] = i;
			int ans = 0, aux = 0;
			int[] end = readInts(in.readLine());
			for (int i = 0; i < end.length; i++)
				if (ini[i] != end[i])
					for (int j = map[end[i]] - 1; j >= i; j--) {
						aux = map[ini[j]];
						map[ini[j]] = map[ini[j + 1]];
						map[ini[j + 1]] = aux;
						aux = ini[j];
						ini[j] = ini[j + 1];
						ini[j + 1] = aux;
						ans++;
					}
			out.append(ans + "\n");
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
