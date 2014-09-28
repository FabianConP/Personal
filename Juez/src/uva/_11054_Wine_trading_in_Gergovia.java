package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _11054_Wine_trading_in_Gergovia {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			if(size == 0)
				break;
			int[] a = readInts(in.readLine());
			Queue<Point> b = new LinkedList<Point>();
			Queue<Point> s = new LinkedList<Point>();
			long work = 0;
			for (int i = 0; i < a.length; i++) {
				if (a[i] > 0)
					b.add(new Point(a[i], i));
				else if (a[i] < 0)
					s.add(new Point(-a[i], i));
				while(!b.isEmpty() && !s.isEmpty()){
					Point buy = b.peek();
					Point sell = s.peek();
					if(buy.x>sell.x){
						work += Math.abs(buy.y-sell.y)*sell.x;
						buy.x -= sell.x;
						s.poll();
					}else{
						work += Math.abs(buy.y-sell.y)*buy.x;
						sell.x -= buy.x;
						b.poll();
					}
				}
			}
			out.append(work+"\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
