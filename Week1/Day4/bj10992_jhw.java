package Day4;

import java.util.Scanner;

public class bj10992_jhw {

    ////이거는 어려워서 찾아보면서 했습니다.(이해하면서 했습니다.) :22 어려웠어요 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//// star 이라는 변수가 크게 이상하진 않지만 *이 출력되는 별의 의미이고 각 라인은 그저 줄넘버의 의미여서 star 보다 len, line, num 등이 더 잘 어울릴 것 같아요
        int star = sc.nextInt();
        		////무난하게 잘 짠거 같아요!
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
