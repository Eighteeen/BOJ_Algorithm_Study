

import java.util.Scanner;

//// 탭 정리가 되어있지 않고, 공백이나 줄바꿈의 간격에 규칙성이 없어 읽기 불편해요. (다른 답안에서도)
public class bj2523_jhw {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int num = sc.nextInt();
		for(int i = 0; i < num; i++) {

			for(int j=0;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
			
			
		}
		for(int i = 0; i<num;i++) {
			for( int j = num-1;j>i;j--) {
				System.out.print("*");
			}
			System.out.println();
        }
        sc.close();
	}
}
