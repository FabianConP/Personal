package uva;
import java.util.ArrayList;
import java.util.Scanner;

public class _10008_Whats_Cryptanalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int times = Integer.parseInt(scan.nextLine());
		String total = "";
		for (int i = 0; i < times; i++)
			total += scan.nextLine();
		while (!total.replaceAll(" ", "").equals(total))
			total = total.replaceAll(" ", "");
		total = total.toUpperCase();
		ArrayList<String> al = new ArrayList<String>();
		int l = 0;
		for (int i = 65; i <= 90; i++) {
			l = total.length()- total.replaceAll(String.valueOf((char) i), "").length();
			al.add(String.valueOf((char) i) + " " + l);
		}
		String aux = "";
		for (int i = 0; i < al.size() - 1; i++) {
			for (int j = i + 1; j < al.size(); j++) {
				if(Integer.parseInt(al.get(i).split(" ")[1])<Integer.parseInt(al.get(j).split(" ")[1])){
					aux = al.get(i);
					al.set(i, al.get(j));
					al.set(j, aux);
				}
			}
		}
		for (int i = 0; i < al.size() - 1; i++) {
			for (int j = i + 1; j < al.size(); j++) {
				if(al.get(i).split(" ")[1].equals(al.get(j).split(" ")[1]) && ((int)al.get(i).charAt(0)>(int)al.get(j).charAt(0))){
					aux = al.get(i);
					al.set(i, al.get(j));
					al.set(j, aux);
				}
			}
		}
		for (int i = 0; i < al.size(); i++) {
			if(Integer.parseInt(al.get(i).split(" ")[1])!=0){
				System.out.println(al.get(i));
			}
		}
	}

}
