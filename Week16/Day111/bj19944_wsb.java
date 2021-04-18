import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// Nett! Klug!
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] gradeInfo = br.readLine().split(" ");
        int N = Integer.parseInt(gradeInfo[0]);
        int M = Integer.parseInt(gradeInfo[1]);

        String output = "NEWBIE!";
        if (M > N) output = "TLE!";
        else if (M > 2) output = "OLDBIE!";

        System.out.println(output);
        br.close();
    }
}