import java.util.Scanner;

public class bj5565_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		//// 입력받을 때마다 바로바로 처리해내게 하면 사용 메모리와 시간 모두 줄어들 것 같아요.:22코드도 더 깔끔해질거 같아요
		////-> 이걸 어떻게 수정해야 될지 잘 모르겠습니다.		
		int num[] = new int[10];
		
		for(int i =0;i<10;i++) {
			
			num[i] = sc.nextInt();
            
			//// 입력값은 백준 문제에 써진 대로만 들어옵니다! 따로 예외 처리를 할 필요가 없어요
			////->지웠습니다!
		}
		//// 나중에 활용성을 생각한 풀이라고 생각해요. 멋져요!
        int num2 =num[0];

		for(int i =1;i<num.length;i++) {
			num2-=num[i];
		}
		System.out.println(num2);

        sc.close();
	}

}
