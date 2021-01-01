package Day4;

import java.util.Scanner;
import java.util.Stack;

public class bj10773_jhw {
		////stack쓰기 딱 좋은 문제답게 정석의 코드를 작성하신거 같아요.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> st = new Stack<>();
		
		int num = sc.nextInt();
        int sum = 0;
        
		for(int i = 0 ; i < num; i++) {
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
