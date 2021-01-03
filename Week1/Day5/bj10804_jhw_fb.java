package 알고르즘;

import java.util.Scanner;

public class bj10804_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		
		int temp;
		
		for(int i = 0 ; i < 10;i++) {
			int  first = sc.nextInt();
			int  second = sc.nextInt();
            ////Reverse기능을 활용하면 더 편리할거같아요!
            ////->Reverse라는 기능을 알아보겠습니다!.
			//// 이 부분은 해석하는데 시간이 좀 걸렸어요. num1에서 1은 왜 뺸걸까, num2에서 1은 왜뺀걸까, 마지막에 num2는 왜 빼주는걸까?
            ////->제가 이렇게 한이유는 인덱스 값은 0부터 시작하나 입력하는 값들은 1부터 시작해서 arr[]배열의 인덱스를 0부터 시작하고자 -을 했습니다!
            //// 변수로 또는 함수로 이름을 붙여 의도를 표현하신다면 더 가독성이 좋아질 수 있을 것 같습니다.
            ////-> 변수명 수정 했습니다!
			 for(int k = first; k < second; k++ ) {
				 temp = arr[k-1];
				 arr[k] = arr[second-1];
				 arr[second-1] = temp;
				 second--;
			 }
		
		}
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		

	}

}
