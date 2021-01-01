
package Day3;

import java.util.Scanner;

public class bj10996_jhw {

	public static void main(String[] args) {
	//고민을 해도 못풀겠어서 답을 찾아서 하나씩 이해하는식으로 작성했습니다.
		Scanner sc = new Scanner(System.in);
		
		int star = sc.nextInt();
		
		int old_num = star-(star/2); // 홀수 
		int even_num = star/2; //짝수
		
		for(int i = 1; i < (2*star) + 1; i++) {
			if(i % 2 != 0) {
				for(int j = 0; j<old_num; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}else {
				for(int j = 0; j < even_num; j++) {
					System.out.print(" *");
				}
				System.out.println();
			}
		}
				
			
	

	}

}

