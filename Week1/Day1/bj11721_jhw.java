import java.util.Scanner;

public class bj11721_jhw {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		
		
        String str = sc.next();
        
		for( int i = 0; i<str.length(); i++) {
			//// 줄바꿈을 뒷 순서에서 하게했다면 && 연산자를 쓰지 않아도 됐는데.. 하는 아쉬움이 듭니다.
			if( i % 10 == 0 && i!= 0 ) {
				System.out.println();
			}
			System.out.print(str.charAt(i));
		}
		
        sc.close();
	}

}
