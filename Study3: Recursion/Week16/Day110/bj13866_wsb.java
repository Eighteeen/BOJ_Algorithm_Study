import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    //// 요런 방법도 있군요. 굳굳
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numArr);

        int team1 = numArr[0] + numArr[3];
        int team2 = numArr[1] + numArr[2];

        System.out.println(Math.abs(team1 - team2));
        br.close();
    }
}