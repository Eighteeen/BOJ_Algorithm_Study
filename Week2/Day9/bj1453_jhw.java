package Day9;

import java.util.Scanner;

public class bj1453_jhw {
	//// 깔끔하네요!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int person = sc.nextInt();
		boolean [] seatArr = new boolean[101];
		int repeat = 0;
		for(int i =0; i < person; i++) {
			//// 백준에서 예외처리는 필요없습니다!
			if(person >100 || person <1) {
				return;
			}
		
			int personSeat = sc.nextInt();

			if(!seatArr[personSeat]) {
				seatArr[personSeat] = true;
			}else {
				repeat++;
			}
			
		}
		System.out.println(repeat);
		
			
		
		
		//인원 수를 입력
		// 자리 입력
		//그럼 앉은 자리를 기억하기 위해 배열 사용
		//같으면 거절 
		// 만약에 i == i+1 i == i+2 ... repeat += 1;
			
		
		
		
	}
}
