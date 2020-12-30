package Day3;

import java.util.Scanner;

//// 전체적으로 깔끔하네요!
public class bj18406_jhw {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		if(str.length()%2 != 0) {
			return;
        }
        
		String first = str.substring(0,str.length()/2);
		String second = str.substring(str.length()/2);
		
		String[] arrFirst = first.split("");
		String[] arrSecond = second.split("");
		
		int sum1 = 0;
        int sum2 = 0;
        
		for(int i = 0; i< arrFirst.length;i++) {
			int firstInt = Integer.parseInt(arrFirst[i]);
			sum1 += firstInt;		
        }
        
		for(int i = 0; i<arrSecond.length;i++) {
			int secondInt = Integer.parseInt(arrSecond[i]);
			sum2+=secondInt;
        }
        
		if(sum1 == sum2) {
			System.out.println("LUCKY");
		}else {
			System.out.println("READY");
        }
        sc.close();
		
		
	}

}
