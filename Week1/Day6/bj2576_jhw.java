package Day6;
import java.util.Scanner;

public class bj2576_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] odd = new int[7];
		int sum = 0;
		int min = 100;
		boolean b = false;
		for(int i = 0;i<odd.length;i++) {
			int num = sc.nextInt();
			odd[i] = num;
			
			if(odd[i]%2 == 1) {
				sum += odd[i];
				b = true;
				if(odd[i]<min) {
					min = odd[i];
				}
			}	
		}
		if(!b) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(min);
		}
		
		
		
	}

}
