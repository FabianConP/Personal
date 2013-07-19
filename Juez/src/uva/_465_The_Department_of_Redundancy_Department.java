package uva;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class _465_The_Department_of_Redundancy_Department {

	/*
	 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category
	 * =24&page=show_problem&problem=425
	 */

	 public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	        LinkedList<Integer> ll = new LinkedList<Integer>();
	        while (scan.hasNextInt()){
	            int aux = scan.nextInt();
	                if (!hm.containsKey(aux)) {
	                    hm.put(aux, 1);
	                    ll.add(aux);
	                } else
	                    hm.put(aux, hm.get(aux) + 1);
	        }
	        for (int i = 0; i < ll.size(); i++) 
	            System.out.println(ll.get(i) + " " + hm.get(ll.get(i)));
	    }
}
