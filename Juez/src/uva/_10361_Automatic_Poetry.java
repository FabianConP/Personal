package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10361_Automatic_Poetry {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			String l1, l2;
			String[] w = new String[2];
			for (int i = 0; i < times; i++) {
				l1 = br.readLine();
				out.append(l1.replaceAll("<", "").replaceAll(">", "")).append(
						"\n");
				l2 = br.readLine();
				out.append(process(l1, l2)).append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static String process(String l11, String l22) {
		String l1 = "", l2 = "", l3 = "", l4 = "";
		int menor = l11.indexOf("<") + 1;
		int mayor = l11.indexOf(">");
		l1 = l11.substring(menor,mayor);
		l11 = l11.substring(mayor+1);
		menor = l11.indexOf("<");
		mayor = l11.indexOf(">");
		l2 = l11.substring(0,menor);
		l3 = l11.substring(menor+1,mayor);
		l4 = l11.substring(mayor+1);
		return l22.replace("...", "") + l3 + l2 +l1 + l4;
	}
}
