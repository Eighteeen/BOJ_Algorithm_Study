package firstDay;

import java.util.Scanner;

public class bj2523_jwh {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 입력 ==>");
		int num = sc.nextInt();
		for(int i = 0; i<num;i++) {
			for(int j=0;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
			
			
		}
		for(int i = 0; i<num;i++) {
			for( int j = num-1;j>i;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
