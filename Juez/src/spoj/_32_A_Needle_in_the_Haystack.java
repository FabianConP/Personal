package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _32_A_Needle_in_the_Haystack {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		boolean first = true, prevNoEmpty = true;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (!first && !prevNoEmpty)
				out.append("\n");
			first = false;
			int sizeNeedle = Integer.parseInt(line.trim());
			String needle = in.readLine().trim(), haystack = in.readLine().trim();
			ArrayList<Integer> coincidencias = KMP(haystack, needle);
			prevNoEmpty = coincidencias.isEmpty();
			for (Integer integer : coincidencias)
				out.append(integer + "\n");
		}
		System.out.print(out);
	}

	public static ArrayList<Integer> KMP(String cadena, String patron) {

		int[] fallas = new int[patron.length() + 1];
		ArrayList<Integer> coincidencias = new ArrayList<Integer>();
		int pos;
		Arrays.fill(fallas, -1);

		// funcion de fallas
		for (int i = 1; i <= patron.length(); i++) {
			pos = fallas[i - 1];

			while (pos != -1 && patron.charAt(pos) != patron.charAt(i - 1)) {
				pos = fallas[pos];
			}

			fallas[i] = pos + 1;
		}

		// cp = pocision cadena, pp = pocision patron
		for (int cp = 0, pp = 0; cp < cadena.length(); cp++) {
			while (pp != -1
					&& (pp == patron.length() || patron.charAt(pp) != cadena.charAt(cp))) {
				pp = fallas[pp];
			}
			pp++;
			if (pp == patron.length()) {
				coincidencias.add(cp + 1 - patron.length());
			}
		}

		return coincidencias;
	}
}
