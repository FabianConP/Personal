package Contest_18_18_12;
import java.math.BigInteger;
import java.util.Scanner;


public class _10070_Leap_Year_or_Not {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int times = 1;
		d: do {
			BigInteger y = scan.nextBigInteger();
			boolean leap = false, hulu = false, bulu = false;
			if(times++!=1)
				System.out.println();
			if((y.mod(new BigInteger("4")).compareTo(new BigInteger("0"))==0 && y.mod(new BigInteger("100")).compareTo(new BigInteger("0"))!=0) || (y.mod(new BigInteger("400")).compareTo(new BigInteger("0"))==0)){
				System.out.println("This is leap year.");
				leap = true;
			}
			if(y.mod(new BigInteger("15")).compareTo(new BigInteger("0"))==0)
				hulu = true;
			if(y.mod(new BigInteger("55")).compareTo(new BigInteger("0"))==0 && leap)
				bulu = true;
			if(hulu)
				System.out.println("This is huluculu festival year.");
			if(bulu)
				System.out.println("This is bulukulu festival year.");
			if(!leap && !hulu && !bulu)
				System.out.println("This is an ordinary year.");
		} while (scan.hasNext());
	}

}
