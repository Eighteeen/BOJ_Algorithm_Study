package Day10;

import java.util.Scanner;

public class bj1551_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numArr = new int[N];
		int K = sc.nextInt();
		
		String number = sc.next();
        String[] numberArr = number.split(",");
        
		for(int i = 0 ; i < N; i++) {
			numArr[i] = Integer.parseInt(numberArr[i]);	
        }
        
		for(int j =0; j < K;j++) {
		    for(int i = 0 ; i < N-j-1; i++) {
			    numArr[i] = numArr[i+1] - numArr[i];
			}
        }
        
        System.out.print(numArr[0]);
        
		for(int i =1 ; i < N-K; i ++) {
			System.out.print("," + numArr[i]);
		}
		
		

	}

}
