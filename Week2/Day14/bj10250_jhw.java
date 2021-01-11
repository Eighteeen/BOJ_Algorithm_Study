package Day14;

import java.util.Scanner;

public class bj10250_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number =sc.nextInt();
		for(int i = 0 ; i < number;i++) {
			int ho = 1;
            int floor = 1;
            
			int H = sc.nextInt();
			int W = sc.nextInt();
            int N = sc.nextInt();
			String[]hotel = new String[H*W+1];
			//// 저 처럼 for문 여러번 안짜도 구현이 가능하네요!
			//// 변수를 a로 설정하셨다가 깃에 올릴 때 hotel이라고 변수 변경을 하셨나봐요 a가 도대체 어디있나 쳐다봤네요.. 백준에서 확인했습니다 ㅠ 최종 파일 백준에서 확인해주세요
			for(int j = 1; j < a.length; j++) {
				//// 굳이 하나하나 저장할 필요는 없어보여요
				if(ho < 10) {
                    hotel[j] = floor + "0" + ho;
                }else {
					hotel[j] = floor + "" + ho;
                }

                if(floor >= H) {
					floor = 1;
					ho += 1;
					if(ho > W) {
						ho = 1;
					}
				}else {
					floor++;
				}	
			}
			System.out.println(hotel[N]);
			
			
		}
		
	}

}
