package Day10;

import java.util.Scanner;

//// 전체적으로 깔끔하게 짜신 것 같습니다: 22깔-끔
public class bj10984_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int season = sc.nextInt();
        for(int i = 0 ; i < season; i++) {
            int subjectNum = sc.nextInt();
            int gradeSum = 0;
            float scoreSum = 0;
			
            for(int j = 0; j < subjectNum; j++) {
                //// Jeom이라는 변수명보다 차라리 Num이라는 게 한번에 읽히기 좋을 것 같아요. 꼭 Num이 아니라 읽히기 좋은 변수로 변경하면 좋을 것 같습니다
                //// Jeom 대신 Score 등으로 했으면 좋았을 것 같아요.
                //// -> 수정했습니다.
                int score = sc.nextInt();
                double subjectScore = sc.nextDouble();
                gradeSum += score;
                scoreSum += score * subjectScore;
            }
            System.out.println(gradeSum + " " + Math.round(scoreSum*10 / gradeSum) /10.0);
        }
	}

}
