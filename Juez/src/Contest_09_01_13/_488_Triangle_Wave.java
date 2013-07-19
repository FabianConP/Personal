package Contest_09_01_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _488_Triangle_Wave {

    public static StringBuilder l(int times, int n) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < times; i++)
            ans.append(String.valueOf(n));
        return ans;
    }

    public static StringBuilder triangle(long amp) {
        ArrayList<StringBuilder> al = new ArrayList<StringBuilder>();
        StringBuilder sb = new StringBuilder();
        StringBuilder aux = new StringBuilder();
        int c = 1;
        for (int j = 0; j < amp; j++) {
            aux = l(j + 1, c++);
            al.add(aux);
            sb.append(aux);
            if (amp!=1 && j  != amp)
                sb.append("\n");       
        }
        Collections.reverse(al);
        for (int j = 1; j < al.size(); j++) {
            aux = al.get(j);
            sb.append(aux);
            if (j + 1 != al.size())
                sb.append("\n");
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder sb = new StringBuilder();
        int f = 0;
            sb.append(((f++ != 0) ? "\n" : ""));
            line = br.readLine();
            int lim = Integer.parseInt(line);
            for (int k = 0; k < lim; k++) {
                br.readLine();
                long amp = Long.parseLong(br.readLine());
                long fre = Long.parseLong(br.readLine());
                StringBuilder sb2 = new StringBuilder();
                sb2 = triangle(amp);
                for (int j = 0; j < fre; j++) {
                    if (j != fre && j != 0)
                        sb.append("\n\n");
                    sb.append(sb2);
                }
                if (lim > 1 && k + 1 < lim)
                    sb.append("\n\n");
            }
        System.out.println(sb);
    }
}