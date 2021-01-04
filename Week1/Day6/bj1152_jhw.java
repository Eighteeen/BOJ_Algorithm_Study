package Day6;
import java.util.Scanner;
import java.util.StringTokenizer;
	//// 깔끔하게 잘 짠거 같습니당 : 22
public class bj1152_jhw {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		//// Token 개수로 판단하는건 생각못했네요. 공백체크도 할 필요가 없어지고 오.. 굳굳
		//// Token으로 세면 편리하지만 구현문제라서 직접 구현해보는 경험도 해보면 좋을 것 같아요! 큰 차이는 아니지만 토큰으로 쓰는 게 시간이 더 걸리더라고요
		////->네 알겠습니다!
		System.out.println(st.countTokens());
		
		
	}
		

}
