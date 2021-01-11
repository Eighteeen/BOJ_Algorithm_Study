package Day14;

import java.util.Scanner;

//// 깔끔하네요! :22 깔끔합니다~~
public class bj10797_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int day = sc.nextInt();
		int n = 0;
		for(int i = 0; i < 5; i++) {
			int carNumber = sc.nextInt();
			if(day == carNumber) {
				n += 1;
			}
		}
		System.out.println(n);

	}

}
