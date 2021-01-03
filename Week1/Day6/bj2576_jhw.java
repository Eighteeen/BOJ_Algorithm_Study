package Day6;
import java.util.Scanner;

public class bj2576_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] odd = new int[7];
		int sum = 0;
		int min = 100;
		boolean b = false;
		for(int i = 0;i<odd.length;i++) {
			int num = sc.nextInt();
			//// 배열을 쓰지 않아도 됐을 것 같습니다. 이전 값을 기억하지 않아도 되니까요. kjh 코드에 그렇게 짰으니 참조해주세요! :22 배열 안써도 가능할거 같습니당 : 333
			odd[i] = num;
			
			if(odd[i]%2 == 1) {
				sum += odd[i];
				//// boolean 변수를 쓰지 않아도, 후에 sum이 0인지를 확인해도 됐었을 것 같습니다 : 22
				b = true;
				if(odd[i]<min) {
					min = odd[i];
				}
			}	
		}
		if(!b) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(min);
		}
		
		
		
	}

}
