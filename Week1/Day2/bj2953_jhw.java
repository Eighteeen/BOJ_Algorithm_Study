
import java.util.Scanner;

public class bj2953_jhw {
		////코드가 약간 복잡한거 같아요.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num[][] = new int[5][4];
		int max = 0;
		int n1= 0;
		int n2= 0;
		int n3= 0;
		int n4= 0;
		int n5= 0;
		//// 모두 n이라는 같은 의미의 변수에다가 값도 모두 0이라면 int n1 = 0, n2 = 0 ... ; 으로 작성하시는 게 더 깔끔할 것 같아요. 배열로 사용하는 게 더 좋을 것 같지만요 ^^
		for(int i = 0; i < num.length;i++) {
			for(int j = 0; j< num[i].length;j++) {
				int num2 = sc.nextInt();
				num[i][j] = num2;
				//// n1, n2, n3, n4, n5가 배열로 만들어졌다면 if문을 쓸 필요 없었을 것 같습니다 : 22
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
		//// 5개의 변수 일때"만" 사용할 수 있는 비효율적인 비교 코드..라고 생각합니다 : 22 아래 코드는 극단적으로 테스크코드를 위해서 쓴 코드처럼 느껴지네요 ㅠ
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
