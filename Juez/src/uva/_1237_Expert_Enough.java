package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1237_Expert_Enough {

	public static Company[] c;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int times = Integer.parseInt(line.trim());
			for (int i = 0; i < times; i++) {
				if (i != 0)
					out.append("\n");
				int nComp = Integer.parseInt(in.readLine().trim());
				c = new Company[nComp];
				for (int j = 0; j < nComp; j++)
					c[j] = new Company(in.readLine().trim());
				int nQueries = Integer.parseInt(in.readLine().trim()), q;
				for (int j = 0; j < nQueries; j++) {
					q = Integer.parseInt(in.readLine().trim());
					int indexAns = 0, countAns = 0;
					for (int k = 0; k < c.length; k++)
						if (c[k].low <= q && q <= c[k].high) {
							indexAns = k;
							countAns++;
							if (countAns >= 2)
								break;
						}
					if (countAns == 1)
						out.append(c[indexAns].name + "\n");
					else
						out.append("UNDETERMINED\n");
				}

			}
		}
		System.out.print(out);
	}

	public static class Company {
		String name;
		int low, high;

		public Company(String info) {
			String[] w = info.split(" ");
			name = w[0];
			low = Integer.parseInt(w[1]);
			high = Integer.parseInt(w[2]);
		}
	}
}
