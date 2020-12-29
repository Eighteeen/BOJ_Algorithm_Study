package Day2;

import java.util.Scanner;

public class bj2445_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int star = sc.nextInt();
		
		for(int i = 0; i<star;i++) {
			for(int j =0;j<=i;j++) {
				System.out.print("*");
			}
			for(int k=(star-i-1)*2;k>0;k--) {
				System.out.print(" ");
			}
			for(int q = 0; q<=i;q++) {
				
				System.out.print("*");
			}
			
			System.out.println();
		}
		for(int i =star-1;i>0;i--) {
			for(int j = i; j>0;j--) {
				System.out.print("*");
			}
			for(int k=1;k<(star-i+1)*2-1;k++) {
				System.out.print(" ");
			}
			for(int q = i;q>0;q--) {
				System.out.print("*");
			}
			System.out.println();
        }
        sc.close();

	}

}
