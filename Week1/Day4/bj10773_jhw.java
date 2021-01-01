package Day4;

import java.util.Scanner;
import java.util.Stack;

public class bj10773_jhw {
		////stack쓰기 딱 좋은 문제답게 정석의 코드를 작성하신거 같아요. :22 :333 깔끔합니다
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> st = new Stack<>();
		
		int num = sc.nextInt();
        int sum = 0;
        
		for(int i = 0 ; i < num; i++) {
			//// 위에서 줄의 길에 해당되는 것을 num이라는 변수로 지정하셔서 num2라는 변수명은 적절하지 않아보여요. :22
			int num2 = sc.nextInt();
			
			if(num2 == 0 ) {
				sum -=st.pop();
			}else {
				sum +=st.push(num2);
			}
		}
        System.out.println(sum);
        sc.close();
    }

}
