import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;

			out.append("").append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
