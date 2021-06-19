import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //// 얼 좀 참신하네요 : 22
        String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "F"};
        float[] ratings = {4.3f, 4.0f, 3.7f, 3.3f, 3.0f, 2.7f, 2.3f, 2.0f, 1.7f, 1.3f, 1.0f, 0.7f, 0.0f};

        String ClangGrade = br.readLine();

        System.out.println(ratings[Arrays.asList(grades).indexOf(ClangGrade)]);
        br.close();
    }
}