import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    ////깔끔하네요.
    public static void main(String[] args) throws IOException {
        int semester = Integer.parseInt(br.readLine());
        int subjectNum;

        for(int i = 0; i < semester; i++){
            subjectNum = Integer.parseInt(br.readLine());
            printGPA(subjectNum);
        }
        System.out.print(sb);
    }

    //// 함수이름 보고 학점 계산해서 출력만 하는 줄 알았는데 코드보니 입력까지 받는다니까 배신당한 기부니에요
    static void printGPA(int len) throws IOException {
        String subjectInfo[];
        int credit, creditSum = 0;
        float grade, gradeSum = 0;
        for(int i = 0; i < len; i++){
            subjectInfo = br.readLine().split(" ");
            credit = Integer.parseInt(subjectInfo[0]);
            grade = Float.parseFloat(subjectInfo[1]);

            creditSum += credit;
            gradeSum += grade * credit;
        }
        sb.append(creditSum).append(" ")
            .append(String.format("%.1f", gradeSum / creditSum)).append("\n");
    }
}