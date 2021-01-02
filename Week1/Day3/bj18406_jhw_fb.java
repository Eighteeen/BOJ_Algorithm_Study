package Day3;

import java.util.Scanner;

//// 전체적으로 깔끔하네요!
public class bj18406_jhw_fb {

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
        ////->수정했습니다!
		int front = 0;
        int back = 0;
                ////양쪽 길이가 같으니까 for문 하나로도 할 수 있었을거 같아요!
                ////->수정했습니다.
		for(int i = 0; i< arrFirst.length;i++) {
			int firstInt = Integer.parseInt(arrFirst[i]);
            front += firstInt;		

            int secondInt = Integer.parseInt(arrSecond[i]);
			back+=secondInt;
        } //// 이런 부분 탭정리가 조금 아쉽습니다 ㅠㅠ
          //// -> 다음부터 더 신경쓰겠습니다.
        
        
		if(front == back) {
			System.out.println("LUCKY");
		}else {
			System.out.println("READY");
        }
        sc.close();
		
		
	}

}
