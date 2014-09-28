package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10878_Decode_the_tape {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			if (line.startsWith("|"))
				out.append(letra(line));
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static char letra(String line) {
		line = line.substring(2, line.length() - 1);
		line = line.replace(".", "");
		line = line.replace("o", "1");
		line = line.replace(" ", "0");
		return (char) Integer.parseInt(line,2);
	}
}
