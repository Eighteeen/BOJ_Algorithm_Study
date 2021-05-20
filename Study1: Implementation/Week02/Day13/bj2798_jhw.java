package Day13;

import java.util.Scanner;

public class bj2798_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		//// 어떤 의미에서 mArr라고 변수 지정을 하신지 모르겠어요
		//// 위의 피드백에 동감해요. 명확한 약어가 아니라면 줄여쓰는건 오히려 독인거같아요
		//// 수정했습니다.
		int [] blackJackScore = new int[N];
		int max = 0;
		int sum = 0;
		
		for(int i =0; i < N; i++) {
			int num = sc.nextInt();
			blackJackScore[i] = num;
		}
		//// for문의 i는 k+1로, j는 i+1로 해주면 if문으로 j,k,i끼리 같아질때 처리를 안해줄 수 있을거 같아요! : 22 :33
		////->수정했습니다
		for(int k = 0 ; k < N-2; k++) {
			for(int i  = k+1; i < N-1; i++) {
				for(int j = i+1 ; j < N; j++ ) {
					sum = blackJackScore[k] + blackJackScore[i] + blackJackScore[j];
					if(sum <= M && sum >= max) {
						max = sum;
					}
				}
			}
		}
		System.out.println(max);
	}

}
