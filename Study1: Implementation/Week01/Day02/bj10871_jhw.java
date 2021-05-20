
////무난하게 잘 짜신거 같아요
import java.util.Scanner;

public class bj10871_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		for(int i=0;i<num1;i++ ) {
			//// num3이라는 이름으로 지으면 무언가의 3번째 숫자인가? 하고 착각할 수 있을 것 같아요 : 22 nowNum 라던가 checkNum 같은 변수명으로 지정하면 좋을 것 같아요
			int num3 = sc.nextInt();
			if(num3<1 && num3 >10000) {
				//// 이 처리는 안 해도 되지만 num3<1 연산자는 붙어있고 num3 >10000 연산자는 떨어져 있어요. 같은 규격으로 코드를 쓰시는 습관을 가지시면 좋을 것 같아요
				return;
			}
			
			if(num2>num3) {
				System.out.println(num3);
			}
        }
        sc.close();

	}

}
