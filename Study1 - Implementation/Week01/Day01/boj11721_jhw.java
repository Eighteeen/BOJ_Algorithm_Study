import java.util.Scanner;

public class bj11721_jhw {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		
		
        String str = sc.next();
        
		for( int i = 0; i<str.length(); i++) {

			//// 줄바꿈을 뒷 순서에서 하게했다면 && 연산자를 쓰지 않아도 됐는데.. 하는 아쉬움이 듭니다.:22 밑에서 줄바꿈 하는게 더 좋을거 같아요 : 333
			//// println(); 이 print(str.charAt(i)); 보다 먼저 나오니까 주도권을 가져가는 느낌이에요. 전체적인 가독성에서 아쉽네요

			if( i % 10 == 0 && i!= 0 ) {
				System.out.println();
			}
			//// 하나씩 print하게 되면 메모리 낭비가 될 것 같아요
			System.out.print(str.charAt(i));

		}
		
        sc.close();
	}

}


