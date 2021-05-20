package Day4;

import java.util.Scanner;

public class bj5597_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		////boolean활용해서 짜는건 생각하지 못했는데,,잘짜신거 같아요! :22 저도요
		boolean [] arr= new boolean[30];
		
		//// 28명만 과제를 제출한 경우에만 미제출자를 구할 수 있는 코드라서 아쉬워요
		//// 전 다른 의견입니다. 문제에서 입력 개수를 정해둔거라 28개만 입력받는게 맞다고 생각해요.
		
		for(int i = 0; i < 28; i++) {
			int a = sc.nextInt();
            arr[a-1] = true;
            sc.close();
		}
	
		for(int i = 0 ; i < 30; i++) {
			if(arr[i] != true) {
				System.out.println(i+1);
			}
        }
        
		

	}

}
