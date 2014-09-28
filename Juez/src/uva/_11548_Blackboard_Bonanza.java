package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11548_Blackboard_Bonanza {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int nWords = Integer.parseInt(in.readLine().trim());
				String[] words = new String[nWords];
				for (int j = 0; j < words.length; j++)
					words[j] = in.readLine().trim();
				int ans = 0;
				for (int j = 0; j < words.length; j++)
					for (int k = j + 1; k < words.length; k++)
						ans = Math.max(check(words[j], words[k]), ans);
				out.append(ans + "\n");
			}
		}
		System.out.print(out);
	}

	public static int check(String aWord, String bWord) {
		char[] a = aWord.toCharArray();
		char[] b = bWord.toCharArray();
		int curSum = 0, sum = 0, aSize = a.length, bSize = b.length, ia;
		for (int i = 0; i < aSize + bSize - 1; i++) {
			ia = i - (bSize - 1);
			curSum = 0;
			for (int j = 0; j < b.length; j++, ia++)
				if (ia >= 0 && ia < aSize && b[j] == a[ia])
					curSum++;
			sum = Math.max(sum, curSum);
		}
		return sum;
	}
}
