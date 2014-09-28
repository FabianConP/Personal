package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _591_Box_of_Bricks {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int sets = Integer.parseInt(line);
			if (sets == 0)
				break;
			int[] columns = returnInts(br.readLine());
			int media = 0, minMoves = 0;
			for (int i = 0; i < columns.length; i++)
				media += columns[i];
			media /= sets;
			for (int i = 0; i < columns.length; i++) 
				if(columns[i]>media)
					minMoves += columns[i]-media;
			out.append("Set #"+times++).append("\n");
			out.append("The minimum number of moves is "+minMoves+".").append("\n\n");
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
