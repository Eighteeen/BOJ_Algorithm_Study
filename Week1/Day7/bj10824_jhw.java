package Day7;
import java.util.Scanner;

public class bj10824_jhw {
        public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		String str3 = sc.next();
		String str4 = sc.next();
		long pre = Long.parseLong(str1+str2);
		long back = Long.parseLong(str3+str4);
        System.out.println(pre+back);
        sc.close();
		
	}
}
