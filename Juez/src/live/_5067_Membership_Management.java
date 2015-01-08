package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _5067_Membership_Management {

	public static HashMap<String, String[]> groups = new HashMap<String, String[]>();
	public static HashMap<String, Integer> counted;

	public static int solve(String group) {
		if (counted.containsKey(group))
			return counted.get(group);
		if (!groups.containsKey(group)) {
			counted.put(group, 0);
			return 1;
		}
		String[] members = groups.get(group);
		int count = 0;
		for (int i = 0; i < members.length; i++)
			if (!counted.containsKey(members[i]))
				count += solve(members[i]);
		counted.put(group, count);
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			groups = new HashMap<String, String[]>();
			String firstGroup = "";
			for (int i = 0; i < n; i++) {
				String data = in.readLine().trim();
				data = data.substring(0, data.length() - 1);
				String[] d = data.split(":");
				String group = d[0];
				String[] members = d[1].split(",");
				groups.put(group, members);
				if (i == 0)
					firstGroup = group;
			}
			counted = new HashMap<String, Integer>();
			int ans = solve(firstGroup);
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
