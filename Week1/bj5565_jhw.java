import java.util.Scanner;

public class bj5565_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int num[] = new int[10];
		
		for(int i =0;i<num.length;i++) {

            num[i] = sc.nextInt();
            
			if(num[0] >10000) {

                System.out.println("10000원이 넘었습니다");
                
				return;
            }	
		}
        int num2 =num[0];

		for(int i =1;i<num.length;i++) {
			num2-=num[i];
		}
		System.out.println(num2);

        sc.close();
	}

}
