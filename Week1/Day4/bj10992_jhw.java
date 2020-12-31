package Day4;

import java.util.Scanner;

public class bj10992_jhw {

    ////이거는 어려워서 찾아보면서 했습니다.(이해하면서 했습니다.)
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int star = sc.nextInt();
        
		for(int i = 1; i <= star; i++) {
			for(int j = 1; j <= star-i; j++) {
				System.out.print(" ");
			}
			for(int j = 1; j <= 2*i-1; j++) {
				if(i != 1 && i != star) {
					if(j == 1 || j == 2*i-1) {
						System.out.print("*");
					}else {
						System.out.print(" ");
					}
				}else {
					System.out.print("*");
				}
			}
			System.out.println();
        }
        sc.close();

	}

}
