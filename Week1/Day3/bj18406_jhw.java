package Day3;

import java.util.Scanner;

//// 전체적으로 깔끔하네요!
public class bj18406_jhw {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		if(str.length()%2 != 0) {
			return;
        }
        
		String first = str.substring(0,str.length()/2);
		String second = str.substring(str.length()/2);
		
		String[] arrFirst = first.split("");
		String[] arrSecond = second.split("");
		
		//// 1/2보다 front/back, left/right 등으로 지었으면 더 직관적이었을 것 같아요
		int sum1 = 0;
        int sum2 = 0;
        
		for(int i = 0; i< arrFirst.length;i++) {
			int firstInt = Integer.parseInt(arrFirst[i]);
			sum1 += firstInt;		
        } //// 이런 부분 탭정리가 조금 아쉽습니다 ㅠㅠ
        
		for(int i = 0; i<arrSecond.length;i++) {
			int secondInt = Integer.parseInt(arrSecond[i]);
			sum2+=secondInt;
        }
        
		if(sum1 == sum2) {
			System.out.println("LUCKY");
		}else {
			System.out.println("READY");
        }
        sc.close();
		
		
	}

}
