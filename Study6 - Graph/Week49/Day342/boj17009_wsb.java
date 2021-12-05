import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ :2
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int apppleScore = 0;
        for (int i = 3; i > 0; i--) {
            apppleScore += Integer.parseInt(br.readLine()) * i;
        }
        int bananaScore = 0;
        for (int i = 3; i > 0; i--) {
            bananaScore += Integer.parseInt(br.readLine()) * i;
        }

        if (apppleScore > bananaScore) {
            System.out.print("A");
        } else if (apppleScore < bananaScore) {
            System.out.println("B");
        } else {
            System.out.println("T");
        }

        br.close();
    }
}