package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _776_Monkeys_in_a_Regular_Forest {

    public static char[][] map;
    public static int n, m;
    public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
            { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } }, ans;

    public static void floodFill(int r, int c, char l, int v) {
        for (int i = 0; i < dir.length; i++)
            if (r + dir[i][0] >= 0 && r + dir[i][0] < n && c + dir[i][1] >= 0
                    && c + dir[i][1] < m
                    && map[r + dir[i][0]][c + dir[i][1]] == l
                    && ans[r + dir[i][0]][c + dir[i][1]] == 0) {
                ans[r + dir[i][0]][c + dir[i][1]] = v;
                floodFill(r + dir[i][0], c + dir[i][1], l, v);
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        StringBuilder out = new StringBuilder();
        do {
            ArrayList<String[]> l = new ArrayList<String[]>();
            if (out.length() != 0 && line.length() != 0 && !line.contains("%"))
                l.add(line.trim().split(" "));
            while ((line = in.readLine()) != null && line.length() != 0 && !line.contains("%"))
                l.add(line.trim().split(" "));
            n = l.size();
            m = l.get(0).length;
            map = new char[n][m];
            ans = new int[n][m];
            String[] row;
            for (int i = 0; i < n; i++) {
                row = l.get(i);
                for (int j = 0; j < m; j++)
                    map[i][j] = row[j].charAt(0);
            }
            int index = 1;
            int[] lengths = new int[m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    if (ans[i][j] == 0) {
                        ans[i][j] = index;
                        floodFill(i, j, map[i][j], index++);
                    }
                    lengths[j] = Math.max(lengths[j], (ans[i][j] + "").length());
                }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                        if (j != 0 )
                            out.append(String.format("%" + (lengths[j]+1) + "d",ans[i][j]));
                        else
                            out.append(String.format("%" + lengths[j]+ "d",ans[i][j]));
                }
                out.append("\n");
            }
            out.append("%\n");
        } while ((line = in.readLine()) != null && line.length() != 0);
        System.out.print(out);
    }
}
