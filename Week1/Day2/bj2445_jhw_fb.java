package Day2;

import java.util.Scanner;

public class bj2445_jhw_fb {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		int star = scanner.nextInt();
		
		//// 입출력 함수를 계속 호출하는 오버헤드 때문에 실행시간이 612ms나 걸리고 있습니다.
		////-> 이 문제는 밑에 피드백이 수정이 되면 바로 수정하겠습니다.
		//// 코드가 약간 비효율적일 수 있다는 생각이 들어요.
		////->어디부분이 비효율적인지 모르겠어서 우선 수정을 안했습니다 전체적으로 비효율적인지 일부분이 비효율적인지 다시 적어주시면 수정하겠습니다.
		for(int i = 0; i < star; i++) {
			for(int j = 0; j <= i; j++) {
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
