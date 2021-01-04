import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔하네요:22 :33
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strNum[] = br.readLine().split(" ");
        long joinNum1 = Long.parseLong((strNum[0] + strNum[1]));
        long joinNum2 = Long.parseLong((strNum[2] + strNum[3]));

        System.out.print(joinNum1 + joinNum2);
    }
}