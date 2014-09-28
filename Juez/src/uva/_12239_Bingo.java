package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class _12239_Bingo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder out = new StringBuilder();
        boolean[] all = new boolean[92];
        while ((line = in.readLine()) != null && line.length() != 0) {
            int[] nb = readInts(line);
            if(nb[0]==0 && nb[1]==0)
                break;
            int[] a = readInts(in.readLine());
            int n = nb[0];
            Arrays.fill(all, false);
            for (int i = 0; i < a.length; i++) 
                for (int j = i + 1; j < a.length; j++) 
                    if(!all[Math.abs(a[i]-a[j])]){
                        all[Math.abs(a[i]-a[j])] = true;
                        n--;
                    }
                out.append((n<=0)?"Y\n":"N\n");
             
        }
        System.out.print(out);
    }
 
    public static int[] readInts(String line) {
        String[] w = line.trim().split(" ");
        int[] a = new int[w.length];
        for (int i = 0; i < w.length; i++)
            a[i] = Integer.parseInt(w[i].trim());
        return a;
    }
}