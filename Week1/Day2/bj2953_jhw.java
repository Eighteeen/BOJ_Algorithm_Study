
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
		for(int i = 0; i < num.length;i++) {
			for(int j = 0; j< num[i].length;j++) {
				int num2 = sc.nextInt();
				num[i][j] = num2;
				//// n1, n2, n3, n4, n5가 배열로 만들어졌다면 if문을 쓸 필요 없었을 것 같습니다 
				////->수정했습니다.
				n1 += num[0][j];
				n2 += num[1][j];
				n3+=num[2][j];
				n4 += num[3][j];
				n5 += num[4][j]; 
			}		
		}
		//// 5개의 변수 일때"만" 사용할 수 있는 비효율적인 비교 코드..라고 생각합니다
	////-> 이렇게 한 이유는 문제에서 5명만 나와있어 문제에 맞게 했습니다. 만약 이게 제가 잘못된 생각을 가지고 있는거면 말씀해주시기 바랍니다.

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
