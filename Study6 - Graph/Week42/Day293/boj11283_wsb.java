import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////깔끔 :2
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char word = br.readLine().charAt(0);
        System.out.println(word - '가' + 1);

        br.close();
    }
}