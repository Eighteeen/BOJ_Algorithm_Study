package Day4;

import java.util.Scanner;
		
public class bj17338_jhw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		
		int numSoongsil  = sc.nextInt();
		int numKorea = sc.nextInt();
		int numHanyang = sc.nextInt();
		//// 깔끔하게 비교해준거 같앙요.
		int sum = numSoongsil + numKorea + numHanyang;
		int min1 =  numSoongsil < numKorea?numSoongsil:numKorea;
        int result = numHanyang < min1 ? numHanyang : min1;

		if(sum >= 100) {
			System.out.println("OK");
		}else if(numKorea == result) {
				System.out.println("Korea");
		}else if(numSoongsil == result) {
			System.out.println("Soongsil");
		}else {
			System.out.println("Hanyang");
        }
        sc.close();
    }
}
