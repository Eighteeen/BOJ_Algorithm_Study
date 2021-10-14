import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 :2
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInfo = br.readLine().split(" ");
        int a = Integer.parseInt(numInfo[0]);
        int b = Integer.parseInt(numInfo[1]);
        int c = Integer.parseInt(numInfo[2]);

        int perimeter = a + b + c;
        int longestLength = Math.max(a, Math.max(b, c));
        int perimeterMinusLongestLength = perimeter - longestLength;

        while (perimeterMinusLongestLength <= longestLength) {
            longestLength--;
            perimeter--;
        }

        System.out.println(perimeter);
        br.close();
    }
}