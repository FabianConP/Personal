package uva;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class _902_Password_Search {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while(in.hasNext()){
			HashMap<String, Integer> subs = new HashMap<String, Integer>();
			int max = 0, curSize = 0;
			int n = Integer.parseInt(in.next().trim());
			String d = in.next(); 
			String sub = "", often = "";
			for (int i = 0; i < d.length()-n-1; i++) {
				sub = d.substring(i,i+n);
				if(!subs.containsKey(sub)){
					subs.put(sub, 1);
					if(max<1 && (max<=1 && often.compareTo(sub)<0)){
						max = 1;
						often = sub;
					}
				}else{
					curSize = subs.get(sub);
					if(max<curSize+1 || (max<=curSize+1 && often.compareTo(sub)<0)){
						max = curSize+1;
						often = sub;
					}
					subs.put(sub, curSize+1);					
				}
			}
			out.append(often+"\n");
		} 
		System.out.print(out);

	}

}
