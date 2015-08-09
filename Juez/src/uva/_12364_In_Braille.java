package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _12364_In_Braille {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		String[][] n = new String[3][10];
		n[0] = ".* *. *. ** ** *. ** ** *. .*".split(" ");
		n[1] = "** .. *. .. .* .* *. ** ** *.".split(" ");
		n[2] = ".. .. .. .. .. .. .. .. .. ..".split(" ");
		HashMap<String, Integer> nums = new HashMap<String, Integer>();
		for (int i = 0; i < 10; i++) {
			String num = "";
			for (int j = 0; j < 3; j++)
				num += n[j][i];
			nums.put(num, i);
		}
		while ((line = in.readLine()) != null && line.length() != 0) {
			int num = Integer.parseInt(line.trim());
			if (num == 0)
				break;
			String type = in.readLine().trim();
			if (type.equals("S")) {
				char[] l = in.readLine().trim().toCharArray();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < l.length; j++) {
						if (j != 0)
							out.append(" ");
						out.append(n[i][l[j] - '0']);
					}
					out.append("\n");
				}
			} else {
				String[][] l = new String[3][num];
				for (int i = 0; i < 3; i++)
					l[i] = in.readLine().trim().split(" ");
				for (int i = 0; i < num; i++)
					out.append(nums.get(l[0][i] + l[1][i] + l[2][i]));
				out.append("\n");
			}
		}
		System.out.print(out);
	}
}
