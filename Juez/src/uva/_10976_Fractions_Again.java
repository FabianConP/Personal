package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10976_Fractions_Again {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int k = Integer.parseInt(line);
			StringBuilder out2 = new StringBuilder();
			int count = 0;
			for (int i = k + 1; i <= 2 * k; i++) 
				if((i*k)%(i-k)==0){
					count++;
					out2.append("1/"+k+" = 1/"+(i*k)/(i-k)+" + 1/"+i+"\n");
				}
			out.append(count+"\n");
			out.append(out2);
		}
		System.out.print(out);
	}
}
