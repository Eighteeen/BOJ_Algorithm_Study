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
		//// 굳이 min에 숫자를 붙인 이유는 뭘까요? min2가 어디있나 괜히 찾아보게 돼요
		//// min result 값을 삼항연산자로 구하시고 싶다면 a < b ? (a < c ? c : a) : b 으로 한줄로 나타낼 수 있어요
		//// 꼭 위 코드가 좋다는 게 아니고 본인이 더 좋다고 생각하는 걸로 선택하시면 될 것 같아요.
		int min1 =  numSoongsil < numKorea?numSoongsil:numKorea;
		//// 위의 삼항연산자는 연산자와 변수값을 공백없이 쓰시고 밑의 삼함연산자는 연산자와 변수값을 공백있게 쓰셨어요. 항상 일관성있게 코드를 작성하시면 좋겠어요
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
