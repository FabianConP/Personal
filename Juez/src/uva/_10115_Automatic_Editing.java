package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _10115_Automatic_Editing {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		String[][] rep;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line.trim());
			if(times == 0)
				break;
			rep = new String[times][2];
			String sentence, original;
			for (int i = 0; i < times; i++) {
				rep[i][0] = br.readLine();
				rep[i][1] = br.readLine();
			}
			sentence = original = br.readLine();
			for (int i = 0; i < rep.length; i++) {
				sentence = sentence.replaceFirst(rep[i][0], rep[i][1]);
				while(!sentence.equals(original)){
					original = sentence;
					sentence = sentence.replaceFirst(rep[i][0], rep[i][1]);
				}
			}
			out.append(sentence).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
