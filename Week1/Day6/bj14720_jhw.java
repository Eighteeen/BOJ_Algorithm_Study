package Day6;

import java.util.Scanner;

public class bj14720_jhw {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int num =sc.nextInt();
		int milkStore = 2;
		int milk = 0;
		for(int i = 0; i < num;i++) {
			int num2 = sc.nextInt();
			if(milkStore == 0 && num2 == 1) {
				milk++;
				milkStore = 1;
			}else if(milkStore == 1 && num2 == 2) {
				milk++;
				milkStore = 2;
			}else if(milkStore == 2 && num2 == 0) {
				milk++;
				milkStore = 0;
			}
		}
		System.out.println(milk);
	}

}

