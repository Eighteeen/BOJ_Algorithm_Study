import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int semester = Integer.parseInt(br.readLine());
        int subjectNum;

        for(int i = 0; i < semester; i++){
            subjectNum = Integer.parseInt(br.readLine());
            printGPA(subjectNum);
        }
        System.out.print(sb);
    }

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