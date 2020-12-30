
//무난하게 잘 짜신거 같아요
import java.util.Scanner;

public class bj10871_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		for(int i=0;i<num1;i++ ) {
			//// num3이라는 이름으로 지으면 무언가의 3번째 숫자인가? 하고 착각할 수 있을 것 같아요
			int num3 = sc.nextInt();
			if(num3<1 && num3 >10000) {
				return;
			}
			
			if(num2>num3) {
				System.out.println(num3);
			}
        }
        sc.close();

	}

}
