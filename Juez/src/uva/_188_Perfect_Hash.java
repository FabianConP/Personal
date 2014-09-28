package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _188_Perfect_Hash {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			String[] words = line.trim().split("[ ]+");
			char[] word;
			int n = words.length;
			int w[] = new int[n], c;
			for (int i = 0; i < n; i++) {
				word = words[i].toCharArray();
				for (int h = 0; h < word.length; h++)
					w[i] = (w[i] << 5) + (word[h] - 'a' + 1);
			}
			Arrays.sort(w);
			c = w[0];
			boolean colision = false;
			while (true) {
				colision = false;
				for (int i = 0; i < n; i++) {
					for (int j = i + 1; j < n; j++) {
						if (fd(c, w[i]) % n == fd(c, w[j]) % n) {
							c = Math.min((fd(c, w[j]) + 1) * w[j],(fd(c, w[i]) + 1) * w[i]);
							colision = true;
							break;
						}
					}
				}
				if (!colision)
					break;
			}
			out.append(line + "\n" + c + "\n\n");
		}
		System.out.print(out);
	}

	public static int fd(int c, int w) {
		return (int) Math.floor((c * 1.0) / w);
	}

}
