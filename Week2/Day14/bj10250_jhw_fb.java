package Day14;

import java.util.Scanner;

public class bj10250_jhw_fb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int number =sc.nextInt();
		int floor;
		int ho;
		for(int i = 0 ; i < number;i++) {
			int H = sc.nextInt();
			int W = sc.nextInt();
			int N = sc.nextInt();
            //// 굳이 하나하나 저장할 필요는 없어보여요
            ////->수정했습니다.
			floor = N % H; 
			ho = N / H;
			
			if( floor == 0) {
				sb.append(H);
			}else {
				ho += 1;
				sb.append(floor);
			}
			sb.append(ho < 10 ? "0" + ho : ho).append("\n");
			
			}
		System.out.print(sb);
			
	}

}
