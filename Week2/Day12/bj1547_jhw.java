package Day12;


import java.util.Scanner;

//// 깔꼼깔꼼
public class bj1547_jhw {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
	
		int numArr[] = {1,0,0};
		int temp;
        
		for(int i = 0; i < num; i++) {
			int X = sc.nextInt(); 
			int Y = sc.nextInt(); 
			
			temp = numArr[X-1];
			numArr[X-1] = numArr[Y-1];
			numArr[Y-1] = temp;
		}
		
		for(int i  = 0; i < numArr.length; i++) {
			if(numArr[i] == 1) {
				System.out.println(i+1);
			}
		}

	}

}
