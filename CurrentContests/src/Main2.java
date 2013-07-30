import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			String[] w = new String[3];
			w[0] = format(line);
			w[1] = format(in.readLine());
			w[2] = format(in.readLine());
			int n = Integer.parseInt(in.readLine());
			HashSet<String> combinations = new HashSet<String>();
			int ndays = 0;
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					if (i != j)
						for (int l = 0; l < 3; l++)
							if (i != j && i != l && j != l)
								combinations.add(w[i] + w[j] + w[l]);
				}
			for (int l = 0; l < n; l++) {
				line = format(in.readLine());
				if (!combinations.contains(line))
					out.append("WA\n");
				else
					out.append("ACC\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static String format(String line) {
		return line.toLowerCase().replaceAll("[-_;]", "");
	}
}
