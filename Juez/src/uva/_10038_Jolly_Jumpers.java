package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _10038_Jolly_Jumpers {

	public static String Jolly(int[] nums, int maxDif) {
		int dif = 0;
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 2; i < nums.length; i++) {
			dif = Math.abs(nums[i] - nums[i - 1]);
			if (dif == 0 || dif >= maxDif)
				return "Not jolly";
			if(al.contains(dif))
				return "Not jolly";
			al.add(dif);
		}
		return "Jolly";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int[] nums = returnInts(line);
			out.append(Jolly(nums, nums[0])).append("\n");
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
