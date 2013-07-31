package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _154_Recycling {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0 || line.equals("#"))
				break;
			StringTokenizer token;
			int city = 0, dep = 0;
			Waste[][] w = new Waste[101][5];
			do {
				dep = 0;
				token = new StringTokenizer(line, ",");
				while (token.hasMoreTokens())
					w[city][dep++] = new Waste(token.nextToken());
				Arrays.sort(w[city]);
				city++;
			} while (!(line = in.readLine()).startsWith("e"));
			int changes = Integer.MAX_VALUE, pos = 0, con = 0;
			for (int i = 0; i < city; i++) {
				con = 0;
				for (int j = 0; j < 5; j++)
					for (int l = 0; l < city; l++)
						if (i != l && w[i][j].dif(w[l][j]))
							con++;
				if (con < changes) {
					pos = i;
					changes = con;
				}
			}
			out.append((pos + 1) + "\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

}

class Waste implements Comparable<Waste> {
	char c, m;

	public Waste(String l) {
		this.c = l.charAt(0);
		this.m = l.charAt(2);
	}

	@Override
	public int compareTo(Waste o) {
		return (int) this.m - (int) o.m;
	}

	public boolean dif(Waste o) {
		return this.c != o.c;
	}
}
