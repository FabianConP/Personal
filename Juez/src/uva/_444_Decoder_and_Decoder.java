package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _444_Decoder_and_Decoder {
	
	public static boolean ok(int n){
		return ((n>=65 && n<=90) || (n>=97 && n<=122) || n==32 || n==33 || n==44 || n==46 || n==58 || n==59 || n==63);
	}

	public static String num(String line) {
		String ans = "";
		while(line.length()>0){
			int n = Integer.parseInt(line.substring(0,2));
			if(ok(n)){
				ans+=((char)n)+"";
				line = line.substring(2);
			}else{
				n = Integer.parseInt(line.substring(0,3));
				ans+=((char)n)+"";
				line = line.substring(3);
			}
		}
		return ans+"\n";
	}

	public static String let(String line) {
		String ans = "";
		for (int i = 0; i < line.length(); i++)
			ans+=((int)line.charAt(i))+"";
		return (new StringBuilder(ans)).reverse().toString()+"\n";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.in"));
		StringBuilder sb = new StringBuilder();
		String line = "";
		d: do {
			line = br.readLine();
			if (line == null)
				break;
			if(line.length()>0)
				if(Character.isDigit(line.charAt(0)))
					sb.append(num((new StringBuilder(line)).reverse().toString()));
				else
					sb.append(let(line));
			else
				sb.append("\n");
		} while (line != null);
		System.out.print(sb);
	}
}
