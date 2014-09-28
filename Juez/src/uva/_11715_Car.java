package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Locale;

public class _11715_Car {

	public static double[] s_a(double[] v){
		double[] ans = new double[2];
		ans[1] = (v[2]-v[1])/v[3]; //a = (Vf-Vo)/t
		ans[0] = (v[1]*v[3])+(ans[1]*(v[3]*v[3])*0.5); //s = Vo*t-0.5*a*t
		return ans;
	}
	
	public static double[] s_t(double[] v){
		double[] ans = new double[2];
		ans[1] = (v[2]-v[1])/v[3]; //t = (Vf-Vo)/a
		ans[0] = (v[1]*ans[1])+(v[3]*(ans[1]*ans[1])*0.5); //s = Vo*t-0.5*a*t
		return ans;
	} 
	
	public static double[] vf_t(double[] v){
		double[] ans = new double[2];
		ans[0] = Math.sqrt((v[1]*v[1])+(2*v[2]*v[3])); // Vf = sqrt(Vo^2+(a*s))
		ans[1] = (ans[0]-v[1])/v[2]; //t = (Vf-Vo)/a 
		return ans;
	} 
	
	public static double[] vo_t(double[] v){
		double[] ans = new double[2];
		ans[0] = Math.sqrt((v[1]*v[1])-(2*v[2]*v[3])); // Vo = sqrt(Vf^2-(a*s))
		ans[1] = (v[1]-ans[0])/v[2]; //t = (Vf-Vo)/a 
		return ans;
	} 
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			double[] val = returnDoubles(line);
			double[] answer = new double[2];
			if(val[0]==0)
				break;
			switch ((int)val[0]) {
			case 1:
				answer = s_a(val);
				break;
			case 2:
				answer = s_t(val);
				break;
			case 3:
				answer = vf_t(val);
				break;
			case 4:
				answer = vo_t(val);
				break;
			}
			System.out.printf(Locale.US, "Case %d: %.3f %.3f\n", times++, answer[0], answer[1]);
		} while (line != null && line.length() != 0);
	}

	public static double[] returnDoubles(String line) {
		String[] a = line.split(" ");
		double[] nums = new double[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Double.parseDouble(a[i]);
		return nums;
	}
}
