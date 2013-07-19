import java.io.BufferedReader;
import java.io.InputStreamReader;

//A. Beautiful Year

public class A_Beautiful_Year {
	
	public static boolean isValid(int y){
		String s = String.valueOf(y);
		int[] d = new int[10];
		for (int i = 0; i < s.length(); i++) {
			if(d[Character.digit(s.charAt(i), 10)]==0)
				d[Character.digit(s.charAt(i), 10)]= 1;
			else
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int n = Integer.parseInt(line);
			n++;
			while(!isValid(n))
				n++;
			sb.append(n).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
