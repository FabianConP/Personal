package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
 
public class _3306_Very_Special_Boxes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder out = new StringBuilder();
        int[] a = new int[1502];
        HashMap<String, Integer> map = new HashMap<String, Integer>(1502);
        ArrayList<String> d = new ArrayList<String>(1502);
        while ((line = in.readLine()) != null && line.length() != 0) {
            int[] nm = readInts(line), c;
            if (nm[0] == 0 && nm[1] == 0)
                break;
            Arrays.fill(a, 0);
            map.clear();
            d.clear();
            int[] box = readInts(in.readLine());
            int index = 0;
            String s = "";
            Arrays.sort(box);
            for (int i = 0; i < nm[1]; i++) {
                c = readInts(in.readLine());
                Arrays.sort(c);
                if (c[0] >= box[0] && c[1] >= box[1] && c[2] >= box[2]) {
                    s = c[0] + " " + c[1] + " " + c[2];
                    if (map.containsKey(s))
                        a[map.get(s)]++;
                    else {
                        d.add(s);
                        map.put(s, index);
                        a[index++]++;
                    }
                }
            }
            long ans = Long.MAX_VALUE;
            for (int i = 0; i < d.size(); i++) {
                s = d.get(i);
                if (a[map.get(s)] >= nm[0]) {
                    c = readInts(s);
                    ans = Math.min(ans, (c[0] * c[1] * c[2])
                            - (box[0] * box[1] * box[2]));
                }
            }
            if (ans == Long.MAX_VALUE)
                out.append("impossible\n");
            else
                out.append(ans + "\n");
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