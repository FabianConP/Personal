package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _11804_Argentina {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		Player[] team = new Player[10];
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			int test = Integer.parseInt(line.trim());
			for (int i = 0; i < test; i++) {
				for (int j = 0; j < 10; j++)
					team[j] = new Player(in.readLine().trim());
				Arrays.sort(team);
				out.append("Case " + times++ + ":\n(");
				Arrays.sort(team, 0, 5, new OrderByName());
				for (int j = 0; j < 5; j++) {
					if (j != 0)
						out.append(", ");
					out.append(team[j]);
				}
				out.append(")\n(");
				Arrays.sort(team, 5, 10, new OrderByName());
				for (int j = 5; j < 10; j++) {
					if (j != 5)
						out.append(", ");
					out.append(team[j]);
				}
				out.append(")\n");
			}
		}
		System.out.print(out);
	}
}

class Player implements Comparable<Player> {
	String name;
	int at, def;

	public Player(String line) {
		String[] w = line.trim().split(" ");
		name = w[0];
		at = Integer.parseInt(w[1].trim());
		def = Integer.parseInt(w[2].trim());
	}

	@Override
	public int compareTo(Player o2) {
		if (this.at == o2.at)
			if (this.def == o2.def)
				return this.name.compareTo(o2.name);
			else
				return this.def - o2.def;
		return o2.at - this.at;
	}

	@Override
	public String toString() {
		return this.name;
	}

}

class OrderByName implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2) {
		return o1.name.compareTo(o2.name);
	}
}
