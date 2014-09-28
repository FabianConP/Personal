package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _291_A_Spyke_Talks {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int n = Integer.parseInt(line), pair = 0;
			int[] code = returnInts(br.readLine());
			boolean inc = false;
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < code.length; i++) {
				if (code[i] != 0 && !inc)
					if (!map.containsKey(code[i]))
						map.put(code[i], 1);
					else {
						int con = map.get(code[i]);
						if (con == 2) {
							inc = true;
							pair--;
						} else{
							map.put(code[i], con + 1);
							pair++;
						}
					}
			}
			if (inc)
				out.append("-1").append("\n");
			else
				out.append(pair).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}
