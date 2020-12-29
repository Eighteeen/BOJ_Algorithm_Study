
import java.util.Scanner;

public class bj10988_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String str  = sc.next();
		
			//// 대개 '합'을 의미하는 sum이 String으로 선언된 게 좀 부자연스럽게 느껴져요. : 22 예약어 같은 느낌인데 선언이 잘못된 느낌입니다.
	    String sum="";
		
		for(int i = str.length()-1;i>=0;i--) {
        
            sum+=str.charAt(i);
		}
			//// 깔끔하게 잘한거 같아용ㅎㅎ
		if(str.equals(sum)) {
			System.out.println(1);
		}else {
			System.out.println(0);
        }
        
        sc.close();
	}

}
