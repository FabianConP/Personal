package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11650_Mirror_Clock {

	public static String format(int n) {
		return ((n + "").length() < 2) ? "0" + n : n + "";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			int[] ans = new int[2];
			for (int i = 0; i < times; i++) {
				String[] data = br.readLine().split(":");
				int[] nums = new int[2];
				nums[0] = Integer.parseInt(data[0]);
				nums[1] = Integer.parseInt(data[1]);
				ans[1] = Math.abs(nums[1] - 60);//Complemento del minutero
				if(nums[1]==0) //Hora en punto
					ans[0] = (nums[0]==12)?12:Math.abs(nums[0]-12);
				else{
					ans[0] = (nums[0]==12)?11:Math.abs(nums[0]-11);
					ans[0] = (ans[0]==0)?12:ans[0];
				}
				out.append(format(ans[0])+":"+format(ans[1]%60)).append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

}
