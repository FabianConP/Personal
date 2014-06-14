package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10502_Counting_Rectangles {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int h = Integer.parseInt(line.trim());
			if (h == 0)
				break;
			int w = Integer.parseInt(in.readLine().trim());
			if (w == 0)
				break;
			char[][] m = new char[h][w];
			for (int i = 0; i < m.length; i++)
				m[i] = in.readLine().trim().toCharArray();
			long ans = 0;
			boolean rec = true;
			for (int i = 0; i < m.length; i++)
				for (int j = 0; j < m[i].length; j++) {
					for (int ii = i; ii < m.length; ii++)
						for (int jj = j; jj < m[i].length; jj++) {
							rec = true;
							for (int ri = i; ri <= ii && rec; ri++)
								for (int rj = j; rj <= jj && rec; rj++)
									rec &= m[ri][rj] == '1';
							ans += rec ? 1 : 0;
						}
				}
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
