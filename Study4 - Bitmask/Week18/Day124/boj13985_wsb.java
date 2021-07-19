import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ~
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] quizInfo = br.readLine().split(" ");
        int a = Integer.parseInt(quizInfo[0]);
        int b = Integer.parseInt(quizInfo[2]);
        int c = Integer.parseInt(quizInfo[4]);

        System.out.println((a + b == c) ? "YES" : "NO");
        br.close();
    }
}