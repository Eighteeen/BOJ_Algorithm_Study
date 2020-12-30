package Day2;

import java.util.Scanner;

public class bj11365_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String st;
	
		while(true) {
			
			st = sc.nextLine();
			
			for(int i =st.length()-1;i>=0;i--) {
				
				
			 if(st.length()>500) {
					//// 예외처리를 항상 하는 습관이 멋있네요. 하지만 백준 문제에서는 처리하지 않아도 좋아용
					return;
				}
			
			//// 백준 문제를 풀 때는 예외처리가 필요없습니다. END로 주어진다고하면 END라고만 와요!
			//// => 코드에 섬세함이 느껴져서 좋네요
			 if(st.equals("END")|| st.equals("end")) {
				 
				 return;
			 }else {
				 System.out.print(st.charAt(i));
				 //// 프린트를 자주하는 문제라면 StringBuilder나 StringBuffer를 이용하면 좋을 것 같아요
				 continue;
			 }
		
			}
			System.out.println();

            sc.close();
        }
		
		
		

	}

}
