package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class _11975_Tele_loto {

    public static HashSet<Point>[] comb = new HashSet[4];
    public static final int[] MAX = { 35, 40, 45, 1 << 20 };

    public static void ini() {
        for (int i = 0; i < 4; i++)
            comb[i] = new HashSet<Point>(50);
        for (int i = 0; i < 5; i++) {
            comb[1].add(new Point(2, i));
            comb[2].add(new Point(i, i));
            comb[2].add(new Point(4 - i, i));
            for (int j = 0; j < 5; j++)
                comb[3].add(new Point(i, j));
        }
        comb[0].add(new Point(0, 0));
        comb[0].add(new Point(4, 0));
        comb[0].add(new Point(0, 4));
        comb[0].add(new Point(4, 4));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder out = new StringBuilder();
        while ((line = in.readLine()) != null && line.length() != 0) {
            int t = Integer.parseInt(line.trim());
            for (int i = 0; i < t; i++) {
                if(i!=0)
                    out.append("\n");
                out.append("Case "+(i+1)+":\n");
                int[] nl = readInts(in.readLine());
                int n = nl[0], l = nl[1];
                int[] order = readInts(in.readLine());
                int[] value = readInts(in.readLine());
                Point p = new Point(0, 0);
                for (int k = 0; k < l; k++) {
                    int[][] ticket = new int[5][5];
                    ini();
                    HashMap<Integer, Point> map = new HashMap<Integer, Point>(50);
                    for (int j = 0; j < ticket.length; j++) {
                        ticket[j] = readInts(in.readLine());
                        for (int h = 0; h < ticket.length; h++)
                            map.put(ticket[j][h], new Point(j, h));
                    }
                    boolean[] ok = new boolean[4];
                    long win = 0;
                    for (int j = 0; j < order.length; j++) {
                        p = map.get(order[j]);
                        if (p != null) 
                        for (int h = 0; h < ok.length; h++) {
                            comb[h].remove(p);
                            if (!ok[h] && j < MAX[h] && comb[h].isEmpty()) {
                                win += value[h];
                                ok[h] = true;
                            }
                        }
                    }
                    out.append(win+"\n");
                }
            }
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
