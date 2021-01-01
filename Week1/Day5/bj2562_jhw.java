package Day5;

import java.util.Scanner;

public class bj2562_jhw {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int numArr[] = new int[9];
		int max=0;
		int index=0;
		for(int i = 0; i < numArr.length; i++) {
			 int str = sc.nextInt();
             numArr[i] = str;
             
			 if(max < numArr[i]) {
				 max = numArr[i];
				 index = i+1;
			 }
			 
		}
		System.out.println(max+"\n" + index);

	}

}
