package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;

public class _11342_Three_square {

	public static String[] pre;
	public static final int LIM = 50000;

	public static void fillPre(int size) {
		pre = new String[size + 1];
		Arrays.fill(pre, "-1");
		String str = "";
		int three;
		for (int a = 0; a <= 224; a++)
			for (int b = a; b <= 224 && (a * a + b * b) <= LIM; b++)
				for (int c = b; c <= 224 && (a * a + b * b + c * c) <= LIM; c++) {
					three = a * a + b * b + c * c;
					str = a + " " + b + " " + c;
					if (pre[three].equals("-1"))	
						pre[three] = str;
				}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringWriter out = new StringWriter();
		fillPre(LIM);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int k = Integer.parseInt(in.readLine());
				out.append(pre[k] + "\n");
			}
		}
		System.out.print(out);
	}

}
