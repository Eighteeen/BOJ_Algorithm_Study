package Day5;

import java.util.Scanner;
	//// 무난하게 잘 짜신거 같아요.
public class bj2562_jhw {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int numArr[] = new int[9];
		int max=0;
		int index=0;
		for(int i = 0; i < numArr.length; i++) {
			//// 탭정리가 아직도 조금 아쉬워요 ㅠㅠ
			 int str = sc.nextInt();
             numArr[i] = str;
             
			 if(max < numArr[i]) {
				 max = numArr[i];
				 index = i+1;
			 }
			 
		}
		System.out.println(max+"\n" + index);

	}

}
