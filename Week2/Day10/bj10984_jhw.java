package Day10;

import java.util.Scanner;

public class bj10984_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int season = sc.nextInt();
        for(int i = 0 ; i < season; i++) {
            int subjectNum = sc.nextInt();
            int gradeSum = 0;
            float scoreSum = 0;
			
            for(int j = 0; j < subjectNum; j++) {
                int subjectJeom = sc.nextInt();
                double subjectScore = sc.nextDouble();
                gradeSum += subjectJeom;
                scoreSum += subjectJeom * subjectScore;
            }
            System.out.println(gradeSum + " " + Math.round(scoreSum*10 / gradeSum) /10.0);
        }
	}

}
