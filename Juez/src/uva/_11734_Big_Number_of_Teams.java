package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11734_Big_Number_of_Teams {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			times = 1;
			for (int i = 0; i < size; i++) {
				String s1 = in.readLine();
				String s2 = in.readLine();
				out.append("Case " + times++ + ": ");
				if (s1.equals(s2))
					out.append("Yes\n");
				else if (s2.equals(remove(s1)))
					out.append("Output Format Error\n");
				else
					out.append("Wrong Answer\n");
			}
		}
		System.out.print(out);
	}

	public static String remove(String s) {
		String r = "";
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) != ' ')
				r += s.charAt(i);
		return r;
	}
}
