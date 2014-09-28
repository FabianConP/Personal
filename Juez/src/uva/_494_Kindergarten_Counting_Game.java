package uva;
import java.util.Scanner;

public class _494_Kindergarten_Counting_Game {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		d: do {
			String line = scan.nextLine();
			char[] l = line.toCharArray();
			boolean w = false;
			int ans = 0;
			for (int i = 0; i < l.length; i++) 
				if(Character.isLetter(l[i])){
					w = true;
				}else if(w){
					ans++;
					w = false;
				}
			if(w)
				ans++;
			System.out.println(ans);
		} while (scan.hasNext());

	}
}