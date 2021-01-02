package Day5;

import java.util.Scanner;

public class bj10804_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		
		int temp;
		
		for(int i = 0 ; i < 10;i++) {
			int  num1 = sc.nextInt();
			int  num2 = sc.nextInt();
				////Reverse기능을 활용하면 더 편리할거같아요!
			 for(int k = num1-1; k < num2; k++ ) {
				 temp = arr[k];
				 arr[k] = arr[num2-1];
				 arr[num2-1] = temp;
				 num2--;
			 }
		
		}
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		

	}

}
