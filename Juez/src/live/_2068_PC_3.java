package live;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2068_PC_3 {

	static class Team implements Comparable<Team> {
		int id, p[], t;

		public Team(int id) {
			super();
			this.id = id;
			p = new int[12];
			t = 0;
		}

		public void update(int index, int value) {
			if (p[index] == 0) {
				p[index] = value;
				t += value;
			}
		}

		@Override
		public int compareTo(Team o) {
			if (t == o.t)
				return id - o.id;
			return o.t - t;
		}
	}

	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		StringBuilder out = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[] nptr = readInts(line);
			int n = nptr[0], p = nptr[1], t = nptr[2], r = nptr[3];
			if (n == 0 && p == 0 && t == 0 && r == 0)
				break;
			Team[] teams = new Team[t];
			for (int i = 0; i < t; i++)
				teams[i] = new Team(i + 1);
			int[] value = readInts(in.readLine());
			for (int i = 0; i < r; i++) {
				int[] info = readInts(in.readLine());
				if (info[3] == 0)
					teams[info[0] - 1].update(info[1] - 1, value[info[1] - 1]);
			}
			out.append("Contest " + n + " Winner:");
			Arrays.sort(teams);
			int max = teams[0].t;
			boolean moreThanOne = false;
			for (int i = 0; i < t; i++)
				if (teams[i].t == max){
					if(moreThanOne)
						out.append(" and");
					moreThanOne = true;
					out.append(" Team " + teams[i].id);
				}
			out.append("\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line);
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
