package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _409_Excuses_Excuses {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int[] ke = returnInts(line);
			String[] keywords = new String[ke[0]];
			String[] excuses = new String[ke[1]];
			int[] occurrences = new int[ke[1]];
			int maxOcurrence = 0;
			for (int i = 0; i < ke[0]; i++)
				keywords[i] = br.readLine().toLowerCase();
			for (int i = 0; i < ke[1]; i++)
				excuses[i] = br.readLine();
			for (int i = 0; i < excuses.length; i++) {
				for (int j = 0; j < keywords.length; j++) {
					Pattern p = Pattern.compile("(\\W|^)" + keywords[j]
							+ "(\\W|$)");
					Matcher m = p.matcher(excuses[i]
							.toLowerCase());
					while (m.find())
						occurrences[i]++;
					maxOcurrence = Math.max(occurrences[i], maxOcurrence);
				}
			}
			out.append("Excuse Set #" + times++).append("\n");
			for (int i = 0; i < occurrences.length; i++)
				if (occurrences[i] == maxOcurrence)
					out.append(excuses[i]).append("\n");
			out.append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}
