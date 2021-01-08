package Day10;


import java.util.ArrayList;
import java.util.Scanner;

public class bj9536_jhw {
//// 탭과 공백 정리는 아예 손 놓으신건가요 ㅠㅠ
////->수정했습니다
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int testCase = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i < testCase; i++) {

			String record = sc.nextLine();
			ArrayList<String> list = new ArrayList<String>();
			String[] recordArr = record.trim().split(" ");

			for(int k = 0; k < recordArr.length; k++) {
				list.add(recordArr[k]);
            }
			while(testCase != 0) {
				String animal = sc.nextLine();
				String[] animalArr =animal.split(" ");
				//// 구현이 깔끔하게 잘 된 것 같습니다
				for(int j = 0 ; j < recordArr.length; j++) {
					if(animalArr[animalArr.length-1].equals(recordArr[j])) {
						list.remove(recordArr[j]);
					}
				}
			if(animal.equals("what does the fox say?")) { break;}
				}
			for(String g : list) {
				System.out.print(g+ " ");
			}
			System.out.println();
			
			
		}
		

	}

}
