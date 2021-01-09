package Day13;

import java.util.Scanner;

public class bj2798_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int [] mArr = new int[N];
		int max = 0;
		int sum = 0;
		
		for(int i =0; i < N; i++) {
			int num = sc.nextInt();
			mArr[i] = num;
		}
		for(int k = 0 ; k < N-2; k++) {
			for(int i  = 1; i < N-1; i++) {
				for(int j = 2 ; j < N; j++ ) {
					if(i != j && k != i && k != j) {
						sum = mArr[k] + mArr[i] + mArr[j];
						if(sum <= M && sum >= max) {
							max = sum;
						}
					}
				}
			}
		
		}
		System.out.println(max);
	}

}
