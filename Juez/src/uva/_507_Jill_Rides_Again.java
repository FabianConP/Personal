package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _507_Jill_Rides_Again {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nRoutes = Integer.parseInt(line.trim());
			for (int i = 0; i < nRoutes; i++) {
				int nStops = Integer.parseInt(in.readLine().trim());
				int s = 1, stop = 0, max = 0, sum = 0;
				Point best = new Point();
				for (int j = 1; j < nStops; j++) {
					stop = Integer.parseInt(in.readLine().trim());
					if (sum + stop < 0) {
						s = j + 1;
						sum = 0;
						continue;
					} else {
						sum += stop;
						if (sum >= max) {
							if (sum > max || (sum  == max && best.y - best.x < j - s + 1)) {
								best.x = s;
								best.y = j + 1;
							}
							max = sum;
						}
					}
				}
				if (max > 0)
					out.append("The nicest part of route " + (i + 1)
							+ " is between stops " + best.x + " and " + best.y
							+ "\n");
				else
					out.append("Route " + (i + 1) + " has no nice parts\n");
			}
		}
		System.out.print(out);
	}
}
