package Contest_18_18_12;
import java.util.LinkedList;
import java.util.Scanner;


public class _382_Perfection {

	public static String format(long suma){
		String ans = "     ";
		return ans.substring(0,5-String.valueOf(suma).length())+suma;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LinkedList<String> ll = new LinkedList<String>();
		int times = 0;
		d: do {
			int n = scan.nextInt();
			if(n==0)
				break d;
			if(times++==0)
				System.out.println("PERFECTION OUTPUT");				
			long suma = 0;
			for (int i = 1; i < n; i++) {
				suma += (n%i==0)?i:0;
			}
			String state = "";
			if(n==suma)
				state="PERFECT";
			else if(suma<n)
				state = "DEFICIENT";
			else
				state = "ABUNDANT";
			ll.add(format(n)+"  "+state);
		} while (scan.hasNext());
		for (int i = 0; i < ll.size(); i++) {
			System.out.println(ll.get(i));
		}
		System.out.println("END OF OUTPUT");
	}

}
