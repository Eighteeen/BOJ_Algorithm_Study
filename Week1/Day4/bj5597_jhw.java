package Day4;

import java.util.Scanner;

public class bj5597_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		////boolean활용해서 짜는건 생각하지 못했는데,,잘짜신거 같아요!
		boolean [] arr= new boolean[30];
		
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
