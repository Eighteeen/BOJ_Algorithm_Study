package Day2;

import java.util.Scanner;

public class bj2445_jhw {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		int star = scanner.nextInt();
		
		//// 입출력 함수를 계속 호출하는 오버헤드 때문에 실행시간이 612ms나 걸리고 있습니다.
		
		//// 코드가 약간 비효율적일 수 있다는 생각이 들어요.

		//// 전체적으로 어떻게 구현해야 할까는 정직하게 잘 하시는 거 같은데 활용성에서 조금 아쉬워요. 가독성과 효율성을 조금씩 더 고려하면 좋을 것 같아요!
		for(int i = 0; i<star;i++) {
			for(int j =0;j<=i;j++) {
				System.out.print("*");
			}
			for(int k = (star-i-1)*2; k > 0; k--) {
				System.out.print(" ");
			}
			for(int q = 0; q <= i; q++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for(int i = star-1; i > 0; i--) {
			for(int j = i; j > 0; j--) {
				System.out.print("*");
			}
			for(int k = 1; k < (star-i+1)*2-1; k++) {
				System.out.print(" ");
			}
			for(int q = i; q > 0; q--) {
				System.out.print("*");
			}
			System.out.println();
        }
        scanner.close();

	}

}
