package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _10300_Ecological_Premium {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				int farmers = Integer.parseInt(br.readLine());
				long total = 0;
				for (int j = 0; j < farmers; j++) {
					int[] values = returnInts(br.readLine());
					total+=values[0]*values[2];
				}
				out.append(total).append("\n");
			}
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
