package Day6;

import java.util.Scanner;

public class bj14720_jhw {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int num =sc.nextInt();
		int milkStore = 2;
		int milk = 0;
		for(int i = 0; i < num;i++) {
			//// num1과 num2엔 별다른 관계가 없는데 변수 네이밍은 관련있어 보이네요
			int num2 = sc.nextInt();
			//// 아까 먹은 우유와 지금 먹을 우유가 둘 다 일치하는지를 비교하고 있는데
			//// '다음 종학이 우유 입맛'만 기억해두고 그에 따라 우유를 먹여줘도 됐을 것 같아요. 참고로 kjh 코드 참조하시면 그렇게 짰습니다:22 저도 같은생각입니다!
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

