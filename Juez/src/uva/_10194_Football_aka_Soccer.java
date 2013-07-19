package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class _10194_Football_aka_Soccer {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in,
				"ISO-8859-1"));
		String line = "";
		StringBuilder out = new StringBuilder();
		OutputStreamWriter cout = new OutputStreamWriter(System.out,
				"ISO-8859-1");
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			String nameCup = "";
			for (int i = 0; i < times; i++) {
				nameCup = br.readLine();
				int nteams = Integer.parseInt(br.readLine());
				HashMap<String, Integer> mapTeams = new HashMap<String, Integer>(
						nteams);
				Team[] teams = new Team[nteams];
				int[][] valuesTeams = new int[nteams][8];
				for (int j = 0; j < nteams; j++) {
					mapTeams.put(line = br.readLine(), j);
					teams[j] = new Team(line);
				}
				int nmatches = Integer.parseInt(br.readLine());
				for (int j = 0; j < nmatches; j++) {
					line = br.readLine();
					String[] nameTeams = nameTeams(line);
					int[] goals = goals(line);
					int t1 = mapTeams.get(nameTeams[0]), t2 = mapTeams
							.get(nameTeams[1]);
					teams[t1].match(goals[0], goals[1]);
					teams[t2].match(goals[1], goals[0]);
				}
				Arrays.sort(teams);
				if (i != 0)
					out.append("\n");
				out.append(nameCup).append("\n");
				for (int j = 0; j < valuesTeams.length; j++)
					out.append((j + 1) + ") " + teams[j] + "\n");
			}

		} while (line != null && line.length() != 0);
		cout.write(out.toString());
		cout.flush();
	}

	static class Team implements Comparable<Team> {
		public String name;
		public int points;
		public int games;
		public int wins;
		public int ties;
		public int loses;
		public int goalDiference;
		public int goalScored;
		public int goalAgainst;

		public Team(String name) {
			this.name = name;
			points = 0;
			games = 0;
			wins = 0;
			ties = 0;
			loses = 0;
			goalDiference = 0;
			goalScored = 0;
			goalAgainst = 0;
		}

		public void match(int gS, int gA) {
			games++;
			if (gS > gA) {
				wins++;
				points += 3;
			} else if (gA > gS) {
				loses++;
			} else {
				ties++;
				points++;
			}
			goalScored += gS;
			goalAgainst += gA;
			goalDiference = goalScored - goalAgainst;
		}

		@Override
		public int compareTo(Team o) {
			if (o.points == this.points)
				if (o.wins == this.wins)
					if (o.goalDiference == this.goalDiference)
						if (o.goalScored == this.goalScored)
							if (o.games == this.games)
								return this.name.toLowerCase().compareTo(
										o.name.toLowerCase());
							else
								return this.games - o.games;
						else
							return o.goalScored - this.goalScored;
					else
						return o.goalDiference - this.goalDiference;
				else
					return o.wins - this.wins;
			else
				return o.points - this.points;
		}

		@Override
		public String toString() {
			String toReturn = name + " " + points + "p, ";
			toReturn += games + "g ";
			toReturn += "(" + wins + "-" + ties + "-" + loses + "), ";
			toReturn += goalDiference + "gd ";
			toReturn += "(" + goalScored + "-" + goalAgainst + ")";
			return toReturn;
		}

	}

	public static String[] nameTeams(String line) {
		String[] ans = new String[2];
		String[] split = line.split("\\#");
		ans[0] = split[0];
		ans[1] = split[2];
		return ans;
	}

	public static int[] goals(String line) {
		String[] split = line.split("\\#");
		String[] split2 = split[1].split("@");
		int[] goals = new int[2];
		goals[0] = Integer.parseInt(split2[0]);
		goals[1] = Integer.parseInt(split2[1]);
		return goals;
	}
}
