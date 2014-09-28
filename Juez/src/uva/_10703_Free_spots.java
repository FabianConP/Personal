package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10703_Free_spots {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		boolean first = true;
		d: do {
			if(!first)
				br.readLine();
			first = false;
			line = br.readLine();
			if (line.equals("0 0 0"))
				break d;
			int[] size = returnInts(line), values;
			long initial = size[0] * size[1];
			boolean[][] board = new boolean[size[0]][size[1]];
			for (int i = 0; i < size[2]; i++) {
				values = returnInts(br.readLine());
				fix(values);
				for (int j = values[0]; j <= values[2]; j++)
					for (int j2 = values[1]; j2 <= values[3]; j2++)
						if (!board[j][j2]) {
							board[j][j2] = true;
							initial--;
						}
			}
			if (initial == 0)
				out.append("There is no empty spots.").append("\n");
			else if (initial == 1)
				out.append("There is one empty spot.").append("\n");
			else
				out.append("There are " + initial + " empty spots.\n");
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

	public static void fix(int[] values) {
		int aux = 0;
		for (int i = 0; i < values.length; i++) 
			values[i]--;
		if (values[1] > values[3]) {
			aux = values[1];
			values[1] = values[3];
			values[3] = aux;
		}
		if (values[0] > values[2]) {
			aux = values[0];
			values[0] = values[2];
			values[2] = aux;
		}
	}
}
