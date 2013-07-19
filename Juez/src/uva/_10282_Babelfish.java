package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class _10282_Babelfish {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		HashMap<String, String> map = new HashMap<String, String>();
		d: do {
			line = br.readLine();
			if (line.length() == 0)
				break d;
			String[] w = line.split(" ");
			map.put(w[1], w[0]);
		} while (line.length() != 0);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			if(!map.containsKey(line))
				out.append("eh").append("\n");
			else
				out.append(map.get(line)).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
