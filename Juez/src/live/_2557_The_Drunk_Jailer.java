package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2557_The_Drunk_Jailer {

	public static boolean[] jail;
	public static int[] ans;

	public static void memo() {
		jail = new boolean[101];
		ans = new int[101];
		for (int i = 1; i < jail.length; i++) {
			if (!jail[i])
				ans[i]++;
			for (int j = 0; j < jail.length; j += i)
				jail[j] = !jail[j];
			ans[i] += ans[i - 1];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		memo();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line), n = 0;
			for (int i = 0; i < size; i++) {
				n = Integer.parseInt(in.readLine().trim());
				out.append(ans[n] + "\n");
			}
		}
		System.out.print(out);
	}

}
