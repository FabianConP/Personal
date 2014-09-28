package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class _195_Anagram {
    
    public static boolean next_permutation(int[] vec) {
        int tmp;
        for (int i = vec.length - 2; i >= 0; i--)
            if (vec[i + 1] > vec[i])
                for (int j = vec.length - 1; j >= 0; j--)
                    if (vec[j] > vec[i]) {
                        tmp = vec[i];
                        vec[i] = vec[j];
                        vec[j] = tmp;

                        for (int k = i + 1, l = vec.length - 1; k < l; k++, l--) {
                            tmp = vec[k];
                            vec[k] = vec[l];
                            vec[l] = tmp;
                        }
                        return true;
                    }
        return false;
    }
    
    public static String printArr(int[] a, HashMap<Integer, Character>  hm){
        String ans = "";
        for (int i = 0; i < a.length; i++)
            ans += String.valueOf(hm.get(a[i]));
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = "";
        HashMap<Integer, Character> hm2 = new HashMap<Integer, Character>();
        int con = 1;
        int[] arr = new int[200];
        for (int i = 0; i < 26; i++) {
            arr[i+'A'] = con;
            hm2.put(con++, (char)('A'+i));
            arr[(char)('A'+i+32)] = con;
            hm2.put(con++, ((char)('A'+i+32)));
        }
        int times = Integer.parseInt(bf.readLine());
        for (int i = 0; i < times; i++) {
            line = bf.readLine();
            char[] lets = line.toCharArray();
            int[] letn = new int[lets.length];
            for (int j = 0; j < lets.length; j++) 
                letn[j] = arr[(int)lets[j]];
            Arrays.sort(letn);
            sb.append(printArr(letn, hm2)).append("\n");
            while(next_permutation(letn))
                sb.append(printArr(letn, hm2)).append("\n");
        }
        System.out.print(sb);
    }
}