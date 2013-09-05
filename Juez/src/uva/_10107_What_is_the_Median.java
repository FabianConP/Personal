package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class _10107_What_is_the_Median {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int size = 0;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int n = Integer.parseInt(line.trim());
			list.add(n);
			Collections.sort(list);
			size++;
			double median = 0;
			if(size%2==0){
				median = (list.get(size/2)+list.get((size/2)-1))/2;
				System.out.println((int)Math.floor(median));
			}else
				System.out.println(list.get(size/2));
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
