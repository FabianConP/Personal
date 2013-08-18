import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Main3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			line = line.replaceAll("[ .]", "");
			String[] w = line.split(",");
			ArrayList<Long> nums = new ArrayList<Long>(w.length);
			ArrayList<String> words = new ArrayList<String>(w.length);
			for (int i = 0; i < w.length; i++) 
				if(Character.isAlphabetic(w[i].charAt(0)))
					words.add(w[i]);
				else
					nums.add(Long.parseLong(w[i]));
			Collections.sort(nums);
			Collections.sort(words);
			for (int i = 0; i < w.length; i++) {
				if(Character.isAlphabetic(w[i].charAt(0)))
					sb.append(words.remove(0));
				else
					sb.append(nums.remove(0));
				if(w.length-1!=i)
					sb.append(", ");
			}
			sb.append(".\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
