package Contest_09_01_13;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10340_All_in_All {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int f = 0;
		String line = "", aux2 = "";
		StringBuilder sb = new StringBuilder();
		d: do {
			line = br.readLine();
			aux2 = line;
			if(aux2==null || aux2.length()==0)
				break d;
			String[] w = line.split(" ");
			boolean ans = true;
			int last = -1, aux;
			f:for (int i = 0; i < w[0].length(); i++) {
				aux = w[1].indexOf(String.valueOf(w[0].charAt(i))); 
				if(aux==-1){
					ans = false;
					break f;
				}else{
					w[1] = w[1].substring(aux+1);
				}
			}
			sb.append(((f++!=0)?"\n":"")+((ans)?"Yes":"No"));
		} while (aux2!=null && aux2.length()!=0);
		System.out.println(sb);
	}
}