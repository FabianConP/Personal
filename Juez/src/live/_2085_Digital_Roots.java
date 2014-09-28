package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class _2085_Digital_Roots {
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0 || line.equals("0"))
				break;
			long n = 0;
			while(line.length()!=1){
				n=0;
				for (int i = 0; i < line.length(); i++) 
					n+=Character.digit(line.charAt(i), 10);
				line = n+"";
			}
			out.append(line+"\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
