
import java.util.Scanner;

public class bj2953_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num[][] = new int[5][4];
		int max = 0;
		int n1= 0;
		int n2= 0;
		int n3= 0;
		int n4= 0;
		int n5= 0;
		for(int i = 0; i < num.length;i++) {
			for(int j = 0; j< num[i].length;j++) {
				int num2 = sc.nextInt();
				num[i][j] = num2;
				if(i == 0) {
					n1 += num[0][j];
					
				}else if( i == 1) {
					n2 += num[1][j];
				}else if(i==2) {
					n3+=num[2][j];
				}else if( i==3) {
					n4 += num[3][j];
				}else if(i == 4) {
					n5 += num[4][j];
                }
			}
			
		}
		if(n1>n2 && n1>n3 && n1>n4 && n1>n5) {
			System.out.println(1+" "+n1);
		}else if(n2>n1 && n2>n3 && n2>n4 && n2>n5) {
			System.out.println(2+" "+n2);
		}
		else if(n3>n1 && n3>n2 && n3>n4 && n3>n5) {
			System.out.println(3+" "+n3);
		}else if(n4>n1 && n4>n2 && n4>n3 && n4>n5) {
			System.out.println(4+" "+n4);
		}else if(n5>n1 && n5>n2 && n5>n4 && n5>n4) {
			System.out.println(5+" "+n5);
		}
		

		sc.close();
	
	}

}
