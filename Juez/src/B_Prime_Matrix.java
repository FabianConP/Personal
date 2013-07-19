import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Prime_Matrix {

	public static boolean[] criba = new boolean[100004];

	public static void numerosPrimos() {
		for (long i = 2; i < 100004; i++) {
			if (criba[(int) i])
				continue;
			for (long j = i; i * j < 100004; j++)
				criba[(int) (i * j)] = true;
		}
	}
	
	public static int nextPrime(int n){
		if(!criba[n])
			return 0;
		for (int i = n+1; i < 100004; i++) 
			if(!criba[i])
				return i-n;
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		criba[0]=criba[1] = true;
		numerosPrimos();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			String[] nums = line.split(" ");
			int aux = 0, min = Integer.MAX_VALUE;
			int f = Integer.parseInt(nums[0]);
			int c = Integer.parseInt(nums[1]);
			int[] columnas = new int[c];
			int[][] m = new int[f][c];
			int[][] m2 = new int[f][c];
			for (int i = 0; i < m.length; i++) {
				aux = 0;
				line = br.readLine();
				nums = line.split(" ");
				for (int j = 0; j < m[i].length; j++) {
					m[i][j] = Integer.parseInt(nums[j]);
					m2[i][j] = nextPrime(m[i][j]);
					aux += m2[i][j];
					columnas[j] += m2[i][j];
				}
				min = (aux < min) ? aux : min;
			}
			for (int i = 0; i < columnas.length; i++)
				min = (columnas[i] < min) ? columnas[i] : min;
			sb.append(min).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}

}
