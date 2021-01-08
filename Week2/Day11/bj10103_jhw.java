package Day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//// 탭 정리가 아직도 아쉽지만
//// 로직은 깔꼼하네요
public class bj10103_jhw {

	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int changScore = 100;
		int sangScore = 100;
		String[] score;
		int number = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < number; i++) {
			score = bf.readLine().split(" ");
			
			int chang = Integer.parseInt(score[0]);
            int sang = Integer.parseInt(score[1]);
            
			if(sang > chang) {
				changScore -= sang;
			}else if(sang < chang){
				sangScore -= chang;
			}else {
				continue;
			}
		}
		System.out.println(changScore);
		System.out.println(sangScore);

	}

}
