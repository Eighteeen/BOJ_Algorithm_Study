package Day6;
import java.util.Scanner;

public class bj2576_jhw_fb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int min = 100;
		
		for(int i = 0; i < 7; i++) {
			int num = sc.nextInt();
			//// 배열을 쓰지 않아도 됐을 것 같습니다. 이전 값을 기억하지 않아도 되니까요. kjh 코드에 그렇게 짰으니 참조해주세요! :22 배열 안써도 가능할거 같습니당 : 333
			////->수정했습니다
			
			if(num % 2 == 1) {
				sum += num;
				//// boolean 변수를 쓰지 않아도, 후에 sum이 0인지를 확인해도 됐었을 것 같습니다 : 22
				////->수정했습니다.
				if(num < min) {
					min = num;
				}
			}	
		}
		if(sum == 0) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(min);
		}
		
		
		
	}

}
