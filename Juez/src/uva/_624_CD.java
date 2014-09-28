package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _624_CD {
	public static int[] tracks;
	public static int taken, ans, dif, totalLength, sum;

	public static void back(int index, int cur) {
		if (totalLength - cur < dif) {
			dif = totalLength - cur;
			sum = cur;
			ans = taken;
		}
		if (index + 1 >= tracks.length)
			return;
		if (cur + tracks[index + 1] <= totalLength) {
			taken |= 1 << (index);
			back(index + 1, cur + tracks[index + 1]);
			taken ^= 1 << (index);
		}
		back(index + 1, cur);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			tracks = retInts(line);
			totalLength = tracks[0];
			taken = 0;
			dif = totalLength;
			back(1, 0);
			for (int i = 1; i < tracks.length; i++)
				if ((ans & (1 << i)) != 0)
					out.append(tracks[i + 1] + " ");
			out.append("sum:" + sum + "\n");
		}
		System.out.print(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

}
