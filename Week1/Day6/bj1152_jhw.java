package Day6;
import java.util.Scanner;
import java.util.StringTokenizer;
	//// 깔끔하게 잘 짠거 같습니당
public class bj1152_jhw {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		//// Token 개수로 판단하는건 생각못했네요. 공백체크도 할 필요가 없어지고 오.. 굳굳
		System.out.println(st.countTokens());
		
		
	}
		

}
