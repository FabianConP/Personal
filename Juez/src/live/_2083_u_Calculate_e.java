package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Locale;

public class _2083_u_Calculate_e {

    public static double[] fac() {
        double[] fact = new double[10];
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++)
            fact[i] = fact[i - 1] * i;
        return fact;
    }

    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat("###.#########");
        double[] fact = fac();
        double[] result = new double[10];
        System.out.println("n e\n- -----------\n0 1");
        result[0] = 1 / fact[0];
        for (int i = 1; i < result.length; i++){
            result[i] = result[i - 1] + (1 / fact[i]);
            System.out.println(i+" "+df.format(result[i]).replace(",", "."));
        }
    }
}
